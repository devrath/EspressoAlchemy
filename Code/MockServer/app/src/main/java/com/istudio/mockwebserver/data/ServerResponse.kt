package com.istudio.mockwebserver.data

import com.google.gson.annotations.SerializedName


data class ServerResponse(
    @SerializedName("id") var id: String? = null,
    @SerializedName("poster") var poster: String? = null
)
