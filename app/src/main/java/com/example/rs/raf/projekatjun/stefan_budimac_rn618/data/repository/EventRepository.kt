package com.example.rs.raf.projekatjun.stefan_budimac_rn618.data.repository

import com.example.rs.raf.projekatjun.stefan_budimac_rn618.data.model.CityTime
import com.example.rs.raf.projekatjun.stefan_budimac_rn618.data.model.Event
import io.reactivex.Observable

interface EventRepository {

    fun fetchCityTime(city: String): Observable<CityTime>
    fun getAllEvents(): Observable<List<Event>>
    fun insertEvent(event: Event)
    fun deleteEvent(eventName: String)
}