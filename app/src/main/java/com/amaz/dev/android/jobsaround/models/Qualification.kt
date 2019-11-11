package com.amaz.dev.android.jobsaround.models


import com.squareup.moshi.Json

data class Qualification(
    @Json(name = "created_at")
    var createdAt: String?,
    @Json(name = "deleted_at")
    var deletedAt: Any?,
    @Json(name = "id")
    var id: Int?,
    @Json(name = "qualification")
    var qualification: String?,
    @Json(name = "updated_at")
    var updatedAt: String?
)