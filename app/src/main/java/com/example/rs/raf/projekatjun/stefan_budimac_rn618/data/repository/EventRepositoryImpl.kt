package com.example.rs.raf.projekatjun.stefan_budimac_rn618.data.repository

import com.example.rs.raf.projekatjun.stefan_budimac_rn618.data.datasource.local.EventDao
import com.example.rs.raf.projekatjun.stefan_budimac_rn618.data.model.Event
import com.example.rs.raf.projekatjun.stefan_budimac_rn618.data.model.EventEntity
import io.reactivex.Observable

class EventRepositoryImpl(
    private val localDataSource: EventDao
) : EventRepository {
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
}