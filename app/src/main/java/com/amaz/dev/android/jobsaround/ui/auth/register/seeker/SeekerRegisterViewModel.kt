package com.amaz.dev.android.jobsaround.ui.auth.register.seeker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amaz.dev.android.jobsaround.models.DataResult
import com.amaz.dev.android.jobsaround.models.SeekerRegisterRequest
import com.amaz.dev.android.jobsaround.repositories.IJobsRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SeekerRegisterViewModel(private val iJobsRepo: IJobsRepo) : ViewModel() {

    var error  = MutableLiveData<String>()

    fun seekerRegister(seekerRegisterRequest: SeekerRegisterRequest) : LiveData<Boolean> {

        var data = MutableLiveData<Boolean>()

        viewModelScope.launch {
            when(val result = withContext(Dispatchers.IO) {iJobsRepo.seekerRegister(seekerRegisterRequest)}){

                is DataResult.Success -> {
                    data.value = result.content
                    error.value =null
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