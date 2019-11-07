package com.amaz.dev.android.jobsaround.models


import com.squareup.moshi.Json

data class SeekerRegisterResponse(
    @Json(name = "avatar")
    var avatar: String?,
    @Json(name = "birthdayGregorian")
    var birthdayGregorian: String?,
    @Json(name = "birthdayHegire")
    var birthdayHegire: String?,
    @Json(name = "created_at")
    var createdAt: String?,
    @Json(name = "email")
    var email: String?,
    @Json(name = "english")
    var english: Int?,
    @Json(name = "englishDegree")
    var englishDegree: Int?,
    @Json(name = "firstName")
    var firstName: String?,
    @Json(name = "id")
    var id: String?,
    @Json(name = "jobType")
    var jobType: Int?,
    @Json(name = "lastName")
    var lastName: String?,
    @Json(name = "latitude")
    var latitude: String?,
    @Json(name = "longitude")
    var longitude: String?,
    @Json(name = "middleName")
    var middleName: String?,
    @Json(name = "national_id")
    var nationalId: String?,
    @Json(name = "phoneNumber")
    var phoneNumber: String?,
    @Json(name = "updated_at")
    var updatedAt: String?
)