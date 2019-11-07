package com.amaz.dev.android.jobsaround.models


import com.squareup.moshi.Json

data class OwnerRegisterResponse(
    @Json(name = "activity")
    var activity: String?,
    @Json(name = "buildName")
    var buildName: String?,
    @Json(name = "created_at")
    var createdAt: String?,
    @Json(name = "email")
    var email: String?,
    @Json(name = "icon")
    var icon: String?,
    @Json(name = "id")
    var id: String?,
    @Json(name = "latitude")
    var latitude: Double?,
    @Json(name = "longitude")
    var longitude: Double?,
    @Json(name = "phone")
    var phone: String?,
    @Json(name = "phoneNumber")
    var phoneNumber: String?,
    @Json(name = "registerImage")
    var registerImage: String?,
    @Json(name = "registrationNumber")
    var registrationNumber: Int?,
    @Json(name = "updated_at")
    var updatedAt: String?,
    @Json(name = "username")
    var username: String?
)