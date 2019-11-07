package com.amaz.dev.android.jobsaround.network

import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

fun createTextBody(value: String): RequestBody {
    return RequestBody.create(MediaType.parse("plain/text"), value)
}

fun createFileBody(key: String, file: File, fileType: String): MultipartBody.Part {
    val requestFile = RequestBody.create(
        MediaType.parse(fileType),
        file
    )

    return MultipartBody.Part.createFormData(key, file.name, requestFile)
}