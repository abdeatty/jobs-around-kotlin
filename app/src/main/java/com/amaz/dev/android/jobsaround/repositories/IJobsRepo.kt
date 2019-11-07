package com.amaz.dev.android.jobsaround.repositories

import com.amaz.dev.android.jobsaround.models.OwnerRegisterRequest
import com.amaz.dev.android.jobsaround.models.DataResult
import com.amaz.dev.android.jobsaround.models.LoginResponse
import com.amaz.dev.android.jobsaround.models.SeekerRegisterRequest

interface IJobsRepo {

    suspend fun ownerRegister(ownerRegisterRequest: OwnerRegisterRequest) : DataResult<Boolean>
    suspend fun seekerRegister(seekerRegisterRequest: SeekerRegisterRequest) : DataResult<Boolean>
    suspend fun login(phoneNumber : String) : DataResult<LoginResponse>
    suspend fun verifiyCode(code : String) : DataResult<Boolean>
}