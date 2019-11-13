package com.amaz.dev.android.jobsaround.ui.createJob

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amaz.dev.android.jobsaround.models.CreateJobRequest
import com.amaz.dev.android.jobsaround.models.DataResult
import com.amaz.dev.android.jobsaround.models.ExperienceYears
import com.amaz.dev.android.jobsaround.models.Qualification
import com.amaz.dev.android.jobsaround.repositories.IJobsRepo
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CreateJobViewModel(private val iJobsRepo: IJobsRepo) : ViewModel() {

    var error = MutableLiveData<String>()


    fun getQualifications() : LiveData<List<Qualification>>{
        var data = MutableLiveData<List<Qualification>>()

        viewModelScope.launch {
            when(val result = withContext(IO) {iJobsRepo.getQualifications()}){
                is DataResult.Success ->{
                    data.value = result.content
                    error.value = null
                }
                is DataResult.Error -> {
                    data.value = null
                    error.value = result.exception.message
                }
            }
        }
        return data
    }


    fun getExperienceYears() : LiveData<List<ExperienceYears>>{

        var data = MutableLiveData<List<ExperienceYears>>()

        viewModelScope.launch {
            when(val result = withContext(IO) {iJobsRepo.getExperienceYears()}){

                is DataResult.Success -> {
                    data.value = result.content
                    error.value = null
                }
                is DataResult.Error -> {
                    data.value = null
                    error.value = result.exception.message
                }
            }
        }
        return data
    }


    fun createJobForOwner(createJobRequest: CreateJobRequest) : LiveData<Boolean>{
        var data = MutableLiveData<Boolean>()
        viewModelScope.launch {
            when(val result = withContext(IO) {iJobsRepo.createJobForOwner(createJobRequest)}){
                is DataResult.Success -> {
                    data.value = result.content
                    error.value = null
                }
                is DataResult.Error -> {
                    data.value = null
                    error.value = result.exception.message
                }
            }
        }
        return data
    }
}