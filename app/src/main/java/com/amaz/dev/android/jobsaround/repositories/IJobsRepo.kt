package com.amaz.dev.android.jobsaround.repositories

import com.amaz.dev.android.jobsaround.models.*

interface IJobsRepo {

    suspend fun ownerRegister(ownerRegisterRequest: OwnerRegisterRequest) : DataResult<Boolean>
    suspend fun seekerRegister(seekerRegisterRequest: SeekerRegisterRequest) : DataResult<Boolean>
    suspend fun login(phoneNumber : String) : DataResult<LoginResponse>
    suspend fun verifiyCode(code : String) : DataResult<Boolean>
    suspend fun getQualifications() : DataResult<List<Qualification>>
    suspend fun getNationalities() : DataResult<List<Nationality>>
}