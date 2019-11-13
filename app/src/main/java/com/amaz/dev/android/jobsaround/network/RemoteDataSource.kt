package com.amaz.dev.android.jobsaround.network

import android.util.Log
import com.amaz.dev.android.jobsaround.models.*
import com.google.android.gms.maps.model.LatLng


class RemoteDataSource(private val api: ApiService) {


    suspend fun ownerRegister(orr: OwnerRegisterRequest) = safeApiCall {
        api.ownerRegister(
            createTextBody(orr.buildName!!),
            createFileBody("icon", orr.icon!!, "icon"),
            createTextBody(orr.activity!!),
            createTextBody(orr.latitude.toString()),
            createTextBody(orr.longitude.toString()),
            createTextBody(orr.registrationNumber!!),
            createFileBody("registerImage", orr.registerImage!!, "registerImage"),
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
            createTextBody(srr.specialization.toString()),
            createTextBody(srr.qualification.toString()),
            createTextBody(srr.description!!),
            createTextBody(srr.latitude.toString()),
            createTextBody(srr.longitude.toString()),
            createTextBody(srr.birthdayHegire!!),
            createTextBody(srr.birthdayGregorian!!),
            createFileBody("avatar", srr.avatar!!, "avatar"),
            createFileBody("resume", srr.resume!!, "resume")
        )
    }

    suspend fun login(phoneNumber: String) = safeApiCall {
        api.login(phoneNumber)
    }

    suspend fun verifiyCode(code: String) = safeApiCall {
        api.verifyCode(code)
    }

    suspend fun getQualifications() = safeApiCall { api.getQualifications() }

    suspend fun getNationalities() = safeApiCall { api.getNationalities() }

    suspend fun getSpecialization(qualificationId : Int) = safeApiCall { api.getSpecialization(qualificationId) }

    suspend fun getExperienceYears()  = safeApiCall { api.getExperienceYears() }

    suspend fun createJobForOwner(cjr: CreateJobRequest) = safeApiCall {


        api.createJobForOwner(cjr.jobTitle!!,cjr.description!!,cjr.gender!!,cjr.qualification!!,cjr.experience!!,cjr.english!!,cjr.englishDegree!!,
            cjr.national!!,cjr.latitude!!,cjr.longitude!!,cjr.jobType!!,cjr.buildName!!,cjr.buildLogo!!)

    }

    suspend fun getOwnerJobs() = safeApiCall { api.getOwnerJobs() }

    suspend fun getOwnerProfile() = safeApiCall { api.getOwnerProfile() }

    suspend fun getNearestJobsForSeeker(latLng: LatLng) = safeApiCall { api.getNearestJobsForSeeker(latLng.latitude,latLng.longitude) }

}