package com.istudio.mockwebserver.view

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.istudio.mockwebserver.data.ServerResponse
import com.istudio.mockwebserver.network.ApiClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import androidx.compose.runtime.State

@HiltViewModel
class MainViewModel @Inject constructor( ) : ViewModel() {

    private val _dataState = mutableStateOf<DataState>(DataState.Loading)
    val dataState: State<DataState> = _dataState

    init {
        fetchData()
    }



    private fun fetchData() {

        val call = ApiClient.apiService.getData()

        call.enqueue(object : Callback<List<ServerResponse>> {
            override fun onResponse(
                call: Call<List<ServerResponse>>, response: Response<List<ServerResponse>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { _dataState.value = DataState.Success(it) }
                } else {
                    _dataState.value = DataState.Error("Failed to fetch data")
                }
            }

            override fun onFailure(call: Call<List<ServerResponse>>, t: Throwable) {
                _dataState.value = DataState.Error(t.message.toString())
            }
        })


    }


}

sealed class DataState {
    data object Loading : DataState()
    data class Success(val data: List<ServerResponse>) : DataState()
    data class Error(val errorMessage: String) : DataState()
}