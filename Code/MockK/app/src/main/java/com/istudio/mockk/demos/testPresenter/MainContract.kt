package com.istudio.mockk.demos.testPresenter

interface MainContract {

    interface Presenter {
        fun fetchData()
    }

    interface View {
        fun onResult(result: List<DataModel>)
        fun onError(error: Throwable)
    }

}