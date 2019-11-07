package com.amaz.dev.android.jobsaround.repositories

import com.amaz.dev.android.jobsaround.models.OwnerRegisterRequest
import com.amaz.dev.android.jobsaround.models.DataResult
import com.amaz.dev.android.jobsaround.models.LoginResponse
import com.amaz.dev.android.jobsaround.models.SeekerRegisterRequest
import com.amaz.dev.android.jobsaround.network.RemoteDataSource

class JobsRepo(private val  remoteDataSource: RemoteDataSource) : IJobsRepo {

    override suspend fun verifiyCode(code: String): DataResult<Boolean> {
        return when(val result = remoteDataSource.verifiyCode(code)){
            is DataResult.Success -> {
                DataResult.Success(true)
            }
            is DataResult.Error -> {
                DataResult.Error(result.exception)
            }
        }

    }

    override suspend fun login(phoneNumber: String): DataResult<LoginResponse> {

        return when(val result = remoteDataSource.login(phoneNumber)){
            is DataResult.Success -> {
                DataResult.Success(result.content)
            }
            is DataResult.Error -> {
                DataResult.Error(result.exception)
            }
        }
    }


    override suspend fun seekerRegister(seekerRegisterRequest: SeekerRegisterRequest): DataResult<Boolean> {

        return when(val result = remoteDataSource.seekerRegister(seekerRegisterRequest)){

            is DataResult.Success -> {
                DataResult.Success(true)
            }
            is DataResult.Error -> {
                DataResult.Error(result.exception)
            }
        }
    }


    override suspend fun ownerRegister(ownerRegisterRequest: OwnerRegisterRequest): DataResult<Boolean> {

        return when(val result = remoteDataSource.ownerRegister(ownerRegisterRequest)){

            is DataResult.Success -> {
                DataResult.Success(true)
            }
            is DataResult.Error -> {
                DataResult.Error(result.exception)
            }
        }

    }
}