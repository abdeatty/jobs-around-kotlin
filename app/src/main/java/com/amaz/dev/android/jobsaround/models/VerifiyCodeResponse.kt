package com.amaz.dev.android.jobsaround.models


import com.squareup.moshi.Json

data class VerifiyCodeResponse(
    @field:Json(name = "access_token")
    var accessToken: String?,
    @field:Json(name = "token_type")
    var tokenType: String?,
    @field:Json(name = "type")
    var type: Int?,
    @Json(name = "user")
    var user: User?
) {
    data class User(
        @field:Json(name = "activity")
        var activity: String?,
        @field:Json(name = "buildName")
        var buildName: String?,
        @field:Json(name = "created_at")
        var createdAt: String?,
        @field:Json(name = "deleted_at")
        var deletedAt: Any?,
        @field:Json(name = "email")
        var email: String?,
        @field:Json(name = "icon")
        var icon: String?,
        @field:Json(name = "id")
        var id: String?,
        @field:Json(name = "latitude")
        var latitude: String?,
        @field:Json(name = "longitude")
        var longitude: String?,
        @field:Json(name = "phone")
        var phone: String?,
        @field:Json(name = "phoneNumber")
        var phoneNumber: String?,
        @field:Json(name = "registerImage")
        var registerImage: String?,
        @field:Json(name = "registrationNumber")
        var registrationNumber: Long?,
        @field:Json(name = "updated_at")
        var updatedAt: String?,
        @field:Json(name = "username")
        var username: String?
    )
}