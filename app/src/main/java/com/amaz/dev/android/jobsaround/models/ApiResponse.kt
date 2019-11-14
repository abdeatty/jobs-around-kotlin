package com.amaz.dev.android.jobsaround.models

import com.squareup.moshi.Json

data class ApiResponse<T>(
    @field:Json(name = "data")
    val data: T?,
//    @field:Json(name = "error")
//    val error: Error?,
    @field:Json(name = "status")
    val status: Int?,
    @field:Json(name = "success")
    val success: Boolean?
)

//jobList class Error(
//    @field:Json(name = "code")
//    val code: Int?,
//    @field:Json(name = "message")
//    val message: String?
//)