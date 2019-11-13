package com.amaz.dev.android.jobsaround.models


import com.squareup.moshi.Json

data class ExperienceYears(
    @field:Json(name = "created_at")
    var createdAt: String?,
    @field:Json(name = "deleted_at")
    var deletedAt: Any?,
    @field:Json(name = "id")
    var id: Int?,
    @field:Json(name = "updated_at")
    var updatedAt: String?,
    @field:Json(name = "years")
    var years: String?
)