package com.amaz.dev.android.jobsaround.models


import com.squareup.moshi.Json

data class CreateJobResponse(
    @Json(name = "buildLogo")
    val buildLogo: Int? = null,
    @Json(name = "buildName")
    val buildName: Int? = null,
    @Json(name = "created_at")
    val createdAt: String? = null,
    @Json(name = "description")
    val description: String? = null,
    @Json(name = "english")
    val english: Int? = null,
    @Json(name = "englishDegree")
    val englishDegree: Int? = null,
    @Json(name = "experiance")
    val experiance: String? = null,
    @Json(name = "gender")
    val gender: Int? = null,
    @Json(name = "id")
    val id: Int? = null,
    @Json(name = "jobTitle")
    val jobTitle: String? = null,
    @Json(name = "jobType")
    val jobType: Int? = null,
    @Json(name = "latitude")
    val latitude: String? = null,
    @Json(name = "longitude")
    val longitude: String? = null,
    @Json(name = "national")
    val national: Int? = null,
    @Json(name = "owner")
    val owner: String? = null,
    @Json(name = "qualification")
    val qualification: String? = null,
    @Json(name = "updated_at")
    val updatedAt: String? = null
)