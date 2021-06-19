package com.example.rs.raf.projekatjun.stefan_budimac_rn618.data.datasource.remote

import com.example.rs.raf.projekatjun.stefan_budimac_rn618.data.model.CityTimeResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface CityTimeService {

    @GET("http://worldtimeapi.org/api/timezone/Europe/{city}")
    fun getCityTime(@Path("city") city: String): Observable<CityTimeResponse>
}