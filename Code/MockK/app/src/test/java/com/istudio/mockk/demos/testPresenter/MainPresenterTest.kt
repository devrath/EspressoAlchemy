package com.istudio.mockk.demos.testPresenter

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.slot
import io.mockk.unmockkAll
import io.mockk.verify
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class MainPresenterTest {

    // View linked - Interface
    @RelaxedMockK
    lateinit var view: MainContract.View

    @RelaxedMockK
    lateinit var dataRepository: DataRepository

    // Presenter linked - Interface
    private lateinit var mainPresenter: MainContract.Presenter

    @Before
    fun setUp() {
        // Initialized the mockK
        MockKAnnotations.init(this)
        // Initialized the presenter class
        mainPresenter = MainPresenter(view,dataRepository)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `test fetch data with an empty list`() {
        // We have mocked the repository so data from teh repository is available
        every { dataRepository.fetchData() } returns listOf()
        // Initiate fetching of data: ---> At this moment the presenter instance will contain the data mocked
        mainPresenter.fetchData()

        // We shall use slot reference to get the data inside the interface
        val captureData = slot<List<DataModel>>()
        // Check that it is called only once --> Also pass the Capture Slot
        verify(exactly = 1) { view.onResult(capture(captureData)) }

        // Perform assertion
        captureData.captured.let { res ->
            assertNotNull(res)
            assert(res.isEmpty())
        }
    }



}