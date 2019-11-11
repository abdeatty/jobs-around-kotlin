package com.amaz.dev.android.jobsaround.network

import com.amaz.dev.android.jobsaround.models.ApiResponse
import com.amaz.dev.android.jobsaround.models.Nationality
import com.amaz.dev.android.jobsaround.models.OwnerRegisterRequest
import com.amaz.dev.android.jobsaround.models.SeekerRegisterRequest


class RemoteDataSource(private val api: ApiService) {


    suspend fun ownerRegister(orr: OwnerRegisterRequest) = safeApiCall {
        api.ownerRegister(
            createTextBody(orr.buildName!!),
            createFileBody("icon", orr.icon!!,"icon"),
            createTextBody(orr.activity!!),
            createTextBody(orr.latitude.toString()),
            createTextBody(orr.longitude.toString()),
            createTextBody(orr.registrationNumber!!),
            createFileBody("registerImage",orr.registerImage!!,"registerImage"),
            createTextBody(orr.username!!),
            createTextBody(orr.phoneNumber!!),
            createTextBody(orr.phone!!),
            createTextBody(orr.email!!)
        )
    }

    suspend fun seekerRegister(srr: SeekerRegisterRequest) = safeApiCall {
        api.seekerRegister(
            createTextBody(srr.firstName!!),
            createTextBody(srr.middleName!!),
            createTextBody(srr.lastName!!),
            createTextBody(srr.gender.toString()),
            createTextBody(srr.national.toString()),
            createTextBody(srr.phoneNumber!!),
            createTextBody(srr.email!!),
            createTextBody(srr.english.toString()),
            createTextBody(srr.englishDegree.toString()),
            createTextBody(srr.jobType.toString()),
            createTextBody(srr.yearsExperience.toString()),
            createTextBody(srr.specialization!!),
            createTextBody(srr.qualification!!),
            createTextBody(srr.description!!),
            createTextBody(srr.latitude.toString()),
            createTextBody(srr.longitude.toString()),
            createTextBody(srr.birthdayHegire!!),
            createTextBody(srr.birthdayGregorian!!),
            createFileBody("avatar",srr.avatar!!,"avatar"),
            createFileBody("resume",srr.resume!!,"resume")
        )
    }

    suspend fun login(phoneNumber : String) = safeApiCall {
        api.login(phoneNumber)
    }

    suspend fun verifiyCode(code : String) = safeApiCall {
        api.verifyCode(code)
    }

    suspend fun getQualifications() = safeApiCall { api.getQualifications() }

    suspend fun getNationalities() = safeApiCall { api.getNationalities() }
}

