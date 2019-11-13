package com.amaz.dev.android.jobsaround.models


import com.squareup.moshi.Json

data class OwnerProfileResponse(
    @Json(name = "activity")
    var activity: String?,
    @Json(name = "buildName")
    var buildName: String?,
    @Json(name = "created_at")
    var createdAt: String?,
    @Json(name = "deleted_at")
    var deletedAt: Any?,
    @Json(name = "email")
    var email: String?,
    @Json(name = "icon")
    var icon: String?,
    @Json(name = "id")
    var id: String?,
    @Json(name = "latitude")
    var latitude: String?,
    @Json(name = "longitude")
    var longitude: String?,
    @Json(name = "phone")
    var phone: String?,
    @Json(name = "phoneNumber")
    var phoneNumber: String?,
    @Json(name = "registerImage")
    var registerImage: String?,
    @Json(name = "registrationNumber")
    var registrationNumber: Long?,
    @Json(name = "updated_at")
    var updatedAt: String?,
    @Json(name = "username")
    var username: String?
)