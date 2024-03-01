package com.istudio.mockk.demos.testPresenter

class MainPresenter(
    private val view: MainContract.View,
    private val dataRepository: DataRepository
) : MainContract.Presenter {

    override fun fetchData() {
        try {
            val result = dataRepository.fetchData()

            view.onResult(
                result.map { it.dataValue }
            )
        } catch (err: Exception) {
            view.onError(err)
        }
    }
}