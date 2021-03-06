package com.example.rs.raf.projekatjun.stefan_budimac_rn618.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "events")
data class EventEntity(
    @ColumnInfo(name = "event_name")
    @PrimaryKey
    val eventName: String,
    val description: String,
    val date: Date,
    val time: String,
    val size: String,
    val url: String
)
