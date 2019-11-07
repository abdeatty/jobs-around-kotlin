package com.amaz.dev.android.jobsaround.models

import com.squareup.moshi.Json

data class ApiError(
    @field:Json(name = "message")
    val message: String?
//    @field:Json(name = "status")
//    val status: Int?,
//    @field:Json(name = "success")
//    val success: Boolean?
)