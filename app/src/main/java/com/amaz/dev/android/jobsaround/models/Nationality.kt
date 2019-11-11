package com.amaz.dev.android.jobsaround.models


import com.squareup.moshi.Json

data class Nationality(
    @Json(name = "created_at")
    var createdAt: String?,
    @Json(name = "id")
    var id: Int?,
    @Json(name = "national")
    var national: String?,
    @Json(name = "updated_at")
    var updatedAt: String?
)