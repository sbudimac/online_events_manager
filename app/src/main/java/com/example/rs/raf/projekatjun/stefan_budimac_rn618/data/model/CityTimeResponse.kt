package com.example.rs.raf.projekatjun.stefan_budimac_rn618.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


data class CityTimeResponse(
    val abbreviation: String,
    val client_ip: String,
    val datetime: String,
    val day_of_week: Int,
    val day_of_year: Int,
    val dst: Boolean,
    val dst_from: String,
    val dst_offset: Int,
    val dst_until: String,
    val raw_offset: Int,
    val timezone: String,
    val unixtime: Long,
    val utc_datetime: String,
    val utc_offset: String,
    val week_number: Int
)

/*
@JsonClass(generateAdapter = true)
data class CityTimeResponse(
    val abbreviation: String,
    @Json(name = "client_ip")
    val clientIp: String,
    @Json(name = "datetime")
    val dateTime: String,
    @Json(name = "day_of_week")
    val dayOfWeek: Int,
    @Json(name = "day_of_year")
    val dayOfYear: Int,
    val dst: Boolean,
    @Json(name = "dst_from")
    val dstForm: String,
    @Json(name = "dst_offset")
    val dstOffset: Int,
    @Json(name = "dst_until")
    val dstUntil: String,
    @Json(name = "raw_offset")
    val rawOffset: Int,
    val timezone: String,
    @Json(name = "unixtime")
    val unixTime: Long,
    @Json(name = "utc_datetime")
    val utcDateTime: String,
    @Json(name = "utc_offset")
    val utcOffset: String,
    @Json(name = "week_number")
    val weekNumber: Int
)
*/