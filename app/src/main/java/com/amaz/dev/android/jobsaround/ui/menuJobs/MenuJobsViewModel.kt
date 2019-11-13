package com.amaz.dev.android.jobsaround.ui.menuJobs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amaz.dev.android.jobsaround.models.DataResult
import com.amaz.dev.android.jobsaround.models.Job
import com.amaz.dev.android.jobsaround.models.JobDetails
import com.amaz.dev.android.jobsaround.repositories.IJobsRepo
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.launch

class MenuJobsViewModel(private val iJobsRepo: IJobsRepo) : ViewModel() {

    var error = MutableLiveData<String>()

    fun getNearestJobsForSeeker(latLng: LatLng) : LiveData<List<JobDetails>>{

        var data = MutableLiveData<List<JobDetails>>()

        viewModelScope.launch {

            when(val result = iJobsRepo.getNearestJobsForSeeker(latLng)){

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