package com.istudio.mockwebserver.view

import androidx.lifecycle.ViewModel
import com.istudio.mockwebserver.data.ServerResponse
import com.istudio.mockwebserver.network.ApiClient
import com.istudio.mockwebserver.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor( ) : ViewModel() {



    fun getData() {

        val call = ApiClient.apiService.getData()

        call.enqueue(object : Callback<ServerResponse> {
            override fun onResponse(
                call: Call<ServerResponse>, response: Response<ServerResponse>
            ) {
                if (response.isSuccessful) {
                    val post = response.body()
                    // Handle the retrieved post data
                } else {
                    // Handle error
                }
            }

            override fun onFailure(call: Call<ServerResponse>, t: Throwable) {
                // Handle failure
            }
        })


    }


}