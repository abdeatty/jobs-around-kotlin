package com.amaz.dev.android.jobsaround.repositories

import android.content.Context
import com.amaz.dev.android.jobsaround.helpers.Constants
import com.amaz.dev.android.jobsaround.models.*
import com.amaz.dev.android.jobsaround.network.Network
import com.amaz.dev.android.jobsaround.network.RemoteDataSource
import com.android.airbag.helpers.SharedPreferencesManager

class JobsRepo(private val  remoteDataSource: RemoteDataSource , private val context: Context) : IJobsRepo {


    override suspend fun getOwnerJobs(): DataResult<List<OwnerJobResponse>> {

        return when(val result = remoteDataSource.getOwnerJobs()){
            is DataResult.Success -> {
                DataResult.Success(result.content)
            }
            is DataResult.Error -> {
                DataResult.Error(result.exception)
            }
        }
    }


    override suspend fun createJobForOwner(createJobRequest: CreateJobRequest): DataResult<Boolean> {

        return when(val result = remoteDataSource.createJobForOwner(createJobRequest)){

            is DataResult.Success ->{
                DataResult.Success(true)
            }
            is DataResult.Error -> {
                DataResult.Error(result.exception)
            }
        }
    }


    override suspend fun getNationalities(): DataResult<List<Nationality>> {
        return when(val result = remoteDataSource.getNationalities()){

            is DataResult.Success ->{
                DataResult.Success(result.content)
            }
            is DataResult.Error -> {
                DataResult.Error(result.exception)
            }
        }


    }


    override suspend fun getQualifications(): DataResult<List<Qualification>> {

        return when(val result = remoteDataSource.getQualifications()){

            is DataResult.Success ->{
                DataResult.Success(result.content)
            }
            is DataResult.Error -> {
                DataResult.Error(result.exception)
            }
        }

    }

    override suspend fun verifiyCode(code: String): DataResult<Boolean> {
        return when(val result = remoteDataSource.verifiyCode(code)){
            is DataResult.Success -> {
                Network.authToken = result.content?.accessToken
                SharedPreferencesManager.setStringValue(context,Constants.TOKEN,result.content?.accessToken)
                SharedPreferencesManager.setIntValue(context,Constants.USER_TYPE,result.content?.type!!)
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