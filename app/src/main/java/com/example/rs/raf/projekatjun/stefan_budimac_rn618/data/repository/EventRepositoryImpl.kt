package com.example.rs.raf.projekatjun.stefan_budimac_rn618.data.repository

import com.example.rs.raf.projekatjun.stefan_budimac_rn618.data.datasource.local.EventDao
import com.example.rs.raf.projekatjun.stefan_budimac_rn618.data.datasource.remote.CityTimeService
import com.example.rs.raf.projekatjun.stefan_budimac_rn618.data.model.CityTime
import com.example.rs.raf.projekatjun.stefan_budimac_rn618.data.model.Event
import com.example.rs.raf.projekatjun.stefan_budimac_rn618.data.model.EventEntity
import io.reactivex.Observable
import timber.log.Timber

class EventRepositoryImpl(
    private val localDataSource: EventDao,
    private val remoteDataSource: CityTimeService
) : EventRepository {
    override fun fetchCityTime(city: String): Observable<CityTime> {
        return remoteDataSource
            .getCityTime(city)
            .map {
                CityTime(it.datetime)
            }
    }

    override fun getAllEvents(): Observable<List<Event>> {
        return localDataSource
            .getAllEvents()
            .map {
                it.map {
                    Event(
                        it.eventName,
                        it.description,
                        it.date,
                        it.time,
                        it.size,
                        it.url
                    )
                }
            }
    }

    override fun insertEvent(event: Event) {
        localDataSource
            .insertEvent(
                EventEntity(
                    event.eventName,
                    event.description,
                    event.date,
                    event.time,
                    event.size,
                    event.url
                )
            )
    }

    override fun deleteEvent(eventName: String) {
        localDataSource
            .deleteEvent(eventName)
    }
}