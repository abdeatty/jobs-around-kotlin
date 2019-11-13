package com.amaz.dev.android.jobsaround.models


import com.squareup.moshi.Json

data class JobDetails(
    @Json(name = "buildLogo")
    var buildLogo: Int?,
    @Json(name = "buildName")
    var buildName: Int?,
    @Json(name = "description")
    var description: String?,
    @Json(name = "distance")
    var distance: Double?,
    @Json(name = "english")
    var english: Int?,
    @Json(name = "englishDegree")
    var englishDegree: Int?,
    @Json(name = "experiance")
    var experiance: Int?,
    @Json(name = "gender")
    var gender: Int?,
    @Json(name = "id")
    var id: Int?,
    @Json(name = "jobTitle")
    var jobTitle: String?,
    @Json(name = "latitude")
    var latitude: String?,
    @Json(name = "longitude")
    var longitude: String?,
    @Json(name = "national")
    var national: National?,
    @Json(name = "owner")
    var owner: OwnerProfileResponse?,
    @Json(name = "qualification")
    var qualification: Int?
) {
    data class National(
        @Json(name = "created_at")
        var createdAt: String?,
        @Json(name = "id")
        var id: Int?,
        @Json(name = "national")
        var national: String?,
        @Json(name = "updated_at")
        var updatedAt: String?
    )


}