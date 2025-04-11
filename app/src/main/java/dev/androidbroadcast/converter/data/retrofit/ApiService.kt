package dev.androidbroadcast.converter.data.retrofit

import retrofit2.http.GET
import retrofit2.http.Query

data class LatestRatesResponse(
    val rates: Map<String, Double>,
    val base: String,
    val date: String
)

interface ApiService {
    @GET("latest")
    suspend fun getLatestRates(
        @Query("access_key") accessKey: String,
        @Query("format") format: Int = 1,
        @Query("symbols") symbols: String = "USD,AUD,CAD,PLN,MXN"
    ): LatestRatesResponse
}