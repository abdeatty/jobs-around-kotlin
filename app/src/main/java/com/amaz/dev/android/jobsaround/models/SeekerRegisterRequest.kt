package com.amaz.dev.android.jobsaround.models

import java.io.File

class SeekerRegisterRequest(
    var firstName: String? = null,
    var middleName: String? = null,
    var lastName: String? = null,
    var gender: Int? = null,
    var national: Int? = null,
    var phoneNumber: String? = null,
    var email: String? = null,
    var latitude: Double? = null,
    var longitude: Double? = null,
    var english: Int? = null,
    var englishDegree: Int? = null,
    var jobType: Int? = null,
    var qualification: Int? = null,
    var specialization: Int? = null,
    var yearsExperience: Int? = null,
    var description: String? = null,
    var birthdayHegire: String? = null, //1/9/1233
    var birthdayGregorian: String? = null,//1/9/1231
    var avatar: File? = null,
    var resume: File? = null

)
