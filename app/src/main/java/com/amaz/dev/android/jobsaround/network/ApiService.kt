package com.amaz.dev.android.jobsaround.network

import com.amaz.dev.android.jobsaround.models.*
import com.amaz.dev.android.jobsaround.network.Network.REQUIRE_AUTHENTICATION
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*


interface ApiService {


    @Multipart
    @POST("owner/register")
    suspend fun ownerRegister(
        @Part("buildName") buildName: RequestBody,
        @Part icon: MultipartBody.Part,
        @Part("activity") activity: RequestBody,
        @Part("latitude") latitude: RequestBody,
        @Part("longitude") longitude: RequestBody,
        @Part("registrationNumber") registrationNumber: RequestBody,
        @Part registerImage: MultipartBody.Part,
        @Part("username") username: RequestBody,
        @Part("phoneNumber") phoneNumber: RequestBody,
        @Part("phone") phone: RequestBody,
        @Part("email") email: RequestBody
    ): ApiResponse<OwnerRegisterResponse>


    @Multipart
    @POST("seekers/register")
    suspend fun seekerRegister(
        @Part("firstName") firstName: RequestBody,
        @Part("middleName") middleName: RequestBody,
        @Part("lastName") lastName: RequestBody,
        @Part("gender") gender: RequestBody,
        @Part("national") national: RequestBody,
        @Part("phoneNumber") phoneNumber: RequestBody,
        @Part("email") email: RequestBody,
        @Part("english") english: RequestBody,
        @Part("englishDegree") englishDegree: RequestBody,
        @Part("jobType") jobType: RequestBody,
        @Part("yearsExperience") yearsExperience: RequestBody,
        @Part("specialization") specialization: RequestBody,
        @Part("qualification") qualification: RequestBody,
        @Part("description") description: RequestBody,
        @Part("latitude") latitude: RequestBody,
        @Part("longitude") longitude: RequestBody,
        @Part("birthdayHegire") birthdayHegire: RequestBody,
        @Part("birthdayGregorian") birthdayGregorian: RequestBody,
        @Part avatar: MultipartBody.Part,
        @Part resume: MultipartBody.Part
    ): ApiResponse<SeekerRegisterResponse>

    @FormUrlEncoded
    @POST("login")
    suspend fun login(@Field("phoneNumber") phoneNumber : String) : ApiResponse<LoginResponse>

    @FormUrlEncoded
    @POST("verify/code")
    suspend fun verifyCode(@Field("code") code : String) : ApiResponse<VerifiyCodeResponse>


    @GET("qualifications")
    suspend fun getQualifications() : ApiResponse<List<Qualification>>

    @FormUrlEncoded
    @POST("qualification/specialization")
    suspend fun getSpecialization(@Field("qualification_id") qualificationId : Int) : ApiResponse<List<Specialization>>

    @GET("nationals")
    suspend fun getNationalities() : ApiResponse<List<Nationality>>

    @GET("experience/years")
    suspend fun getExperienceYears() : ApiResponse<List<ExperienceYears>>



    @GET("owner/job/list")
    @Headers("$REQUIRE_AUTHENTICATION: true")
    suspend fun getOwnerJobs() : ApiResponse<List<Job>>


    @FormUrlEncoded
    @POST("owner/job/adds")
    @Headers("$REQUIRE_AUTHENTICATION: true")
    suspend fun createJobForOwner(
        @Field("jobTitle") jobTitle : String,
                                  @Field("description") description : String,
                                  @Field("gender") gender : Int,
                                  @Field("qualification") qualification : Int,
                                  @Field("experiance") experience : Int,
                                  @Field("english") english : Int,
                                  @Field("englishDegree") englishDegree : Int,
                                  @Field("national") national : Int,
                                  @Field("latitude") latitude : Double,
                                  @Field("longitude") longitude : Double,
                                  @Field("jobType") jobType : Int,
                                  @Field("buildName") buildName : Int,
                                  @Field("buildLogo") buildLogo : Int
    ) : ApiResponse<CreateJobResponse>


    @GET("owner/getUser")
    @Headers("$REQUIRE_AUTHENTICATION: true")
    suspend fun getOwnerProfile() : ApiResponse<OwnerProfileResponse>

    @FormUrlEncoded
    @POST("seekers/nearest/work")
    @Headers("$REQUIRE_AUTHENTICATION: true")
    suspend fun getNearestJobsForSeeker(@Field("latitude") latitude : Double,
                                  @Field("longitude") longitude: Double) : ApiResponse<List<JobDetails>>

}







