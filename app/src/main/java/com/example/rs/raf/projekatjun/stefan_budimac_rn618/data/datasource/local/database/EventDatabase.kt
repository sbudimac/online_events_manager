package com.example.rs.raf.projekatjun.stefan_budimac_rn618.data.datasource.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rs.raf.projekatjun.stefan_budimac_rn618.data.datasource.local.EventDao
import com.example.rs.raf.projekatjun.stefan_budimac_rn618.data.datasource.local.converters.DateConverter
import com.example.rs.raf.projekatjun.stefan_budimac_rn618.data.model.EventEntity

@Database(
    entities = [EventEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateConverter::class)
abstract class EventDatabase : RoomDatabase() {

    abstract fun getEventDao(): EventDao
}