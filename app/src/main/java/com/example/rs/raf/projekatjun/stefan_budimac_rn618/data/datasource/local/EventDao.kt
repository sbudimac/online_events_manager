package com.example.rs.raf.projekatjun.stefan_budimac_rn618.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rs.raf.projekatjun.stefan_budimac_rn618.data.model.EventEntity
import io.reactivex.Observable

@Dao
abstract class EventDao {

    @Query("SELECT * FROM events")
    abstract fun getAllEvents(): Observable<List<EventEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertEvent(eventEntity: EventEntity)

    @Query("DELETE FROM events WHERE event_name LIKE :eventName")
    abstract fun deleteEvent(eventName: String)
}