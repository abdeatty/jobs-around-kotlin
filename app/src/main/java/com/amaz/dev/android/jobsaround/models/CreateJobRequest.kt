package com.amaz.dev.android.jobsaround.models

class CreateJobRequest(
    var jobTitle : String ? =null,
    var description : String ? =null,
    var gender : Int ? =null,
    var qualification : Int ? =null,
    var experience : Int ? =null,
    var english : Int ? =null,
    var englishDegree : Int ? =null,
    var national : Int ? =null,
    var latitude : Double ? =null,
    var longitude : Double ? =null,
    var jobType : Int ? =null,
    var buildName : Int ? =null,
    var buildLogo : Int ? =null
)

