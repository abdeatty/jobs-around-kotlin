package com.amaz.dev.android.jobsaround.ui.ownerJobs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amaz.dev.android.jobsaround.models.DataResult
import com.amaz.dev.android.jobsaround.models.Job
import com.amaz.dev.android.jobsaround.repositories.IJobsRepo
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OwnerJobsViewModel(private val iJobsRepo: IJobsRepo) : ViewModel() {

    var error = MutableLiveData<String>()

    fun getOwnerJobs() : LiveData<List<Job>>{

        var data = MutableLiveData<List<Job>>()

        viewModelScope.launch {
            when(val result = withContext(IO) {iJobsRepo.getOwnerJobs()}){

                is DataResult.Success ->{
                    data.value = result.content
                    error.value = null
                }
                is DataResult.Error ->{
                    data.value = null
                    error.value = result.exception.message
                }
            }
        }
        return data
    }
}