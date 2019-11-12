package com.amaz.dev.android.jobsaround.ui.ownerJobs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amaz.dev.android.jobsaround.models.DataResult
import com.amaz.dev.android.jobsaround.models.OwnerJobResponse
import com.amaz.dev.android.jobsaround.repositories.IJobsRepo
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OwnerJobsViewModel(private val iJobsRepo: IJobsRepo) : ViewModel() {

    var error = MutableLiveData<String>()

    fun getOwnerJobs() : LiveData<List<OwnerJobResponse>>{

        var data = MutableLiveData<List<OwnerJobResponse>>()

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