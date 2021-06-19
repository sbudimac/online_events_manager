package com.example.rs.raf.projekatjun.stefan_budimac_rn618.modules

import com.example.rs.raf.projekatjun.stefan_budimac_rn618.data.datasource.local.database.EventDatabase
import com.example.rs.raf.projekatjun.stefan_budimac_rn618.data.repository.EventRepository
import com.example.rs.raf.projekatjun.stefan_budimac_rn618.data.repository.EventRepositoryImpl
import com.example.rs.raf.projekatjun.stefan_budimac_rn618.presentaion.viewmodel.EventViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val eventModule = module {
    viewModel { EventViewModel(eventRepository = get()) }

    single<EventRepository> { EventRepositoryImpl(localDataSource = get()) }

    single { get<EventDatabase>().getEventDao() }
}