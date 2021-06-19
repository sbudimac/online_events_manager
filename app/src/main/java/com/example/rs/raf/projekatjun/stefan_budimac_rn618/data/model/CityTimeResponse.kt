package com.example.rs.raf.projekatjun.stefan_budimac_rn618.data.model

data class CityTimeResponse(
    val abbreviation: String,
    val clientIp: String,
    val dateTime: String,
    val dayOfWeek: Int,
    val dayOfYear: Int,
    val dst: Boolean,
    val dstForm: String,
    val dstOffset: Int,
    val dstUntil: String,
    val rawOffset: Int,
    val unixTime: Long,
    val utcDateTime: String,
    val utcOffset: String,
    val weekNumber: Int
)
