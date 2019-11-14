package com.amaz.dev.android.jobsaround.models


import com.squareup.moshi.Json

data class JobDetails(
    @Json(name = "active")
    var active: Int?,
    @Json(name = "buildLogo")
    var buildLogo: Int?,
    @Json(name = "buildName")
    var buildName: Int?,
    @Json(name = "created_at")
    var createdAt: String?,
    @Json(name = "description")
    var description: String?,
    @Json(name = "english")
    var english: Int?,
    @Json(name = "englishDegree")
    var englishDegree: Int?,
    @Json(name = "experiance")
    var experiance: String?,
    @Json(name = "gender")
    var gender: Int?,
    @Json(name = "id")
    var id: Int?,
    @Json(name = "isApplied")
    var isApplied: Int?,
    @Json(name = "isOwner")
    var isOwner: Int?,
    @Json(name = "jobTitle")
    var jobTitle: String?,
    @Json(name = "jobType")
    var jobType: Int?,
    @Json(name = "latitude")
    var latitude: String?,
    @Json(name = "longitude")
    var longitude: String?,
    @Json(name = "national")
    var national: String?,
    @Json(name = "number")
    var number: String?,
    @Json(name = "owner")
    var owner: OwnerProfileResponse?,
    @Json(name = "qualification")
    var qualification: String?,
    @Json(name = "updated_at")
    var updatedAt: String?
)
