package com.example.auto_data.api

import retrofit2.Call
import retrofit2.http.GET

interface AutoDataService {
    @GET("/?code=cd04cc3e0d8aa911f67a01298af87061")
    fun getBrands(): Call<Brands>
}
