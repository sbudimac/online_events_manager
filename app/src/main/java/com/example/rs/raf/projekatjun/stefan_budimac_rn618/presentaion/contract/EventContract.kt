package com.example.rs.raf.projekatjun.stefan_budimac_rn618.presentaion.contract

import androidx.lifecycle.LiveData
import com.example.rs.raf.projekatjun.stefan_budimac_rn618.data.model.CityTime
import com.example.rs.raf.projekatjun.stefan_budimac_rn618.data.model.Event
import com.example.rs.raf.projekatjun.stefan_budimac_rn618.presentaion.view.state.EventsState

interface EventContract {

    interface ViewModel {
        val eventState: LiveData<EventsState>
        val cityTime: LiveData<CityTime>

        fun fetchCityTime(city: String)
        fun getAllEvents()
        fun insertEvent(event: Event)
        fun deleteEvent(eventName: String)
    }
}