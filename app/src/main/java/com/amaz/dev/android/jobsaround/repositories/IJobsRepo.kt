package com.amaz.dev.android.jobsaround.repositories

import com.amaz.dev.android.jobsaround.models.*
import com.google.android.gms.maps.model.LatLng

interface IJobsRepo {

    suspend fun ownerRegister(ownerRegisterRequest: OwnerRegisterRequest) : DataResult<Boolean>
    suspend fun seekerRegister(seekerRegisterRequest: SeekerRegisterRequest) : DataResult<Boolean>
    suspend fun login(phoneNumber : String) : DataResult<LoginResponse>
    suspend fun verifiyCode(code : String) : DataResult<Boolean>
    suspend fun getQualifications() : DataResult<List<Qualification>>
    suspend fun getNationalities() : DataResult<List<Nationality>>
    suspend fun createJobForOwner(createJobRequest: CreateJobRequest) : DataResult<Boolean>
    suspend fun getOwnerJobs() : DataResult<List<Job>>
    suspend fun getOwnerProfile() : DataResult<OwnerProfileResponse>
    suspend fun getNearestJobsForSeeker(latLng: LatLng) : DataResult<List<JobDetails>>
    suspend fun getSpecialization(qualificationId : Int) : DataResult<List<Specialization>>
    suspend fun getExperienceYears() : DataResult<List<ExperienceYears>>
    suspend fun getJobDetails(jobId : Int) : DataResult<JobDetails>
}