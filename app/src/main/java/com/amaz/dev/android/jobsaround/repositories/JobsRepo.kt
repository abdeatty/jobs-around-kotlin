package com.amaz.dev.android.jobsaround.repositories

import android.content.Context
import android.util.Log
import com.amaz.dev.android.jobsaround.helpers.Constants
import com.amaz.dev.android.jobsaround.models.*
import com.amaz.dev.android.jobsaround.network.Network
import com.amaz.dev.android.jobsaround.network.RemoteDataSource
import com.android.airbag.helpers.SharedPreferencesManager
import com.google.android.gms.maps.model.LatLng

class JobsRepo(private val  remoteDataSource: RemoteDataSource , private val context: Context) : IJobsRepo {


    override suspend fun getJobDetails(jobId: Int): DataResult<JobDetails> {

        return when(val result = remoteDataSource.getJobDetailsForSeeker(jobId)){

            is DataResult.Success -> {
                DataResult.Success(result.content)
            }
            is DataResult.Error -> {
                DataResult.Error(result.exception)
            }
        }

    }


    override suspend fun getNearestJobsForSeeker(latLng: LatLng): DataResult<List<JobDetails>> {


        return when(val result = remoteDataSource.getNearestJobsForSeeker(latLng)){

            is DataResult.Success -> {
                DataResult.Success(result.content)
            }
            is DataResult.Error -> {
                DataResult.Error(result.exception)
            }
        }
    }


    override suspend fun getOwnerProfile(): DataResult<OwnerProfileResponse> {


        return when(val result = remoteDataSource.getOwnerProfile()){
            is DataResult.Success -> {
                DataResult.Success(result.content)
            }

            is DataResult.Error -> {
                DataResult.Error(result.exception)
            }
        }
    }


    override suspend fun getOwnerJobs(): DataResult<List<Job>> {

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



    override suspend fun getSpecialization(qualificationId: Int): DataResult<List<Specialization>> {


        return when(val result = remoteDataSource.getSpecialization(qualificationId)){

            is DataResult.Success ->{
                DataResult.Success(result.content)
            }
            is DataResult.Error -> {
                DataResult.Error(result.exception)
            }
        }
    }

    override suspend fun getExperienceYears(): DataResult<List<ExperienceYears>> {

        return when(val result = remoteDataSource.getExperienceYears()){

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