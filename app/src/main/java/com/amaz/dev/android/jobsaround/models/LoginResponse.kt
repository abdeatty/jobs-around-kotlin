package com.amaz.dev.android.jobsaround.models


import com.squareup.moshi.Json

data class LoginResponse(
    @Json(name = "code")
    var code: String?,

    @Json(name = "created_at")
    var createdAt: String?,

    @Json(name = "id")
    var id: Int?,

    @Json(name = "updated_at")
    var updatedAt: String?,

    @Json(name = "user")
    var user: String?
)