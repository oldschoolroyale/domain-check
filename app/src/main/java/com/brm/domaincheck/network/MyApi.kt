package com.brm.domaincheck.network

import com.brm.domaincheck.model.Domain
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Rakhimjonov Shokhsulton on 19,октябрь,2021
 * at Mayasoft LLC,
 * Tashkent, UZB.
 */
interface MyApi {

    @GET("domains/{domain-name}")
    fun getDomain(
        @Header("x-rapidapi-host") host: String,
        @Header("x-rapidapi-key") key: String,
        @Path("domain-name") name: String
    ): Call<Domain>
}