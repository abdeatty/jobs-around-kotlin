package com.amaz.dev.android.jobsaround.models


import com.squareup.moshi.Json

data class OwnerJobResponse(
    @field:Json(name = "buildLogo")
    val buildLogo: Int? = null,
    @field:Json(name = "buildName")
    val buildName: Int? = null,
    @field:Json(name = "created_at")
    val createdAt: String? = null,
    @field:Json(name = "description")
    val description: String? = null,
    @field:Json(name = "english")
    val english: Int? = null,
    @field:Json(name = "englishDegree")
    val englishDegree: Int? = null,
    @field:Json(name = "experiance")
    val experiance: Int? = null,
    @field:Json(name = "gender")
    val gender: Int? = null,
    @field:Json(name = "id")
    val id: Int? = null,
    @field:Json(name = "jobTitle")
    val jobTitle: String? = null,
    @field:Json(name = "jobType")
    val jobType: Int? = null,
    @field:Json(name = "latitude")
    val latitude: String? = null,
    @field:Json(name = "longitude")
    val longitude: String? = null,
    @field:Json(name = "national")
    val national: Int? = null,
    @field:Json(name = "owner")
    val owner: String? = null,
    @field:Json(name = "qualification")
    val qualification: Int? = null,
    @field:Json(name = "updated_at")
    val updatedAt: String? = null
)