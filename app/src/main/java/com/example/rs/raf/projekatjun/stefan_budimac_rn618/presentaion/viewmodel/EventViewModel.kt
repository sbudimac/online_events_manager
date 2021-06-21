package com.example.rs.raf.projekatjun.stefan_budimac_rn618.presentaion.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rs.raf.projekatjun.stefan_budimac_rn618.data.model.CityTime
import com.example.rs.raf.projekatjun.stefan_budimac_rn618.data.model.Event
import com.example.rs.raf.projekatjun.stefan_budimac_rn618.data.model.Resource
import com.example.rs.raf.projekatjun.stefan_budimac_rn618.data.repository.EventRepository
import com.example.rs.raf.projekatjun.stefan_budimac_rn618.presentaion.contract.EventContract
import com.example.rs.raf.projekatjun.stefan_budimac_rn618.presentaion.view.state.EventsState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import kotlin.concurrent.thread

class EventViewModel(
    private val eventRepository: EventRepository
) : ViewModel(), EventContract.ViewModel {

    private val subscriptions = CompositeDisposable()
    override val eventState: MutableLiveData<EventsState> = MutableLiveData()
    override val cityTime: MutableLiveData<CityTime> = MutableLiveData()
    override fun fetchCityTime(city: String) {
        val subscription = eventRepository
            .fetchCityTime(city)
            .startWith(CityTime(""))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    cityTime.value = it
                },
                {
                    eventState.value = EventsState.Error("Error happened while fetching data from the server")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAllEvents() {
        val subscription = eventRepository
            .getAllEvents()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    eventState.value = EventsState.Success(it)
                },
                {
                    eventState.value = EventsState.Error("Error happened while fetching data from database")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun insertEvent(event: Event) {
        thread {
            eventRepository.insertEvent(event)
        }
    }

    override fun deleteEvent(eventName: String) {
        thread {
            eventRepository.deleteEvent(eventName)
        }
    }
}