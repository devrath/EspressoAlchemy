package com.istudio.mockwebserver.network

import com.istudio.mockwebserver.data.ServerResponse
import retrofit2.Call
import retrofit2.http.GET

// https://raw.githubusercontent.com/devrath/Sample-Data/master/CurrencyAppSampleData/Currencies.json
interface ApiService {

    companion object {
        private const val currency = "Currencies.json"
    }

    @GET(currency)
    fun getData(): Call<ServerResponse>

}

