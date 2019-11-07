package com.amaz.dev.android.jobsaround.network

import android.util.Log
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import retrofit2.HttpException
import com.amaz.dev.android.jobsaround.models.ApiError
import com.amaz.dev.android.jobsaround.models.ApiResponse
import com.amaz.dev.android.jobsaround.models.DataResult

import java.io.IOException


val moshi: Moshi = Moshi.Builder().build()
val jsonAdapter: JsonAdapter<ApiError> = moshi.adapter<ApiError>(ApiError::class.java)
suspend fun <T> safeApiCall(apiCall: suspend () -> ApiResponse<T>): DataResult<T> {

    return try {

        var result = apiCall()
        if (result.success == true) {
            val data = result.data
            return DataResult.Success(data)
        }
        Log.i("Error", "safe call api try error")
        return DataResult.Error(Exception("حدث خطا ما. برجاء المحاولة مرة اخري."))

    } catch (e: Exception) {
        Log.i("Error", e.message)
        when (e) {
            is HttpException -> {
                val errorBodyString = e.response()?.errorBody()?.string()
                var errorBodyJson: ApiError? = null
                if (errorBodyString != null) {
                    try {
                        errorBodyJson = jsonAdapter.fromJson(errorBodyString)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                when (e.code()) {
                    in 300 until 400 -> {
                        DataResult.Error(Exception("غير مصرح لك."))
                    }
                    in 400 until 500 -> {
                        DataResult.Error(
                            Exception(
                                errorBodyJson?.message  ?: "برجاء التاكد من البيانات."
                            )
                        )
                    }
                    in 500 until 600 -> {
                        DataResult.Error(Exception("حدث خطا في السيرفر. برجاء المحاولة بعد قليل."))
                    }
                    else -> {
                        DataResult.Error(Exception("حدث خطا ما. برجاء المحاولة مرة اخري."))
                    }
                }
            }
            is IOException -> {
                DataResult.Error(Exception("لا يوجد اتصال برجاء التاكد من اتصالك بالانترنت."))
            }
            else -> {
                DataResult.Error(Exception("حدث خطا ما. برجاء المحاولة مرة اخري."))
            }
        }
    }
}
