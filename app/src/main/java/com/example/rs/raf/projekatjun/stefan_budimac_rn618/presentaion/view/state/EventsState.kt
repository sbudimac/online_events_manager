package com.example.rs.raf.projekatjun.stefan_budimac_rn618.presentaion.view.state

import com.example.rs.raf.projekatjun.stefan_budimac_rn618.data.model.Event

sealed class EventsState {
    object Loading: EventsState()
    object DataFetched: EventsState()
    data class Success(val events: List<Event>): EventsState()
    data class Error(val message: String): EventsState()
}