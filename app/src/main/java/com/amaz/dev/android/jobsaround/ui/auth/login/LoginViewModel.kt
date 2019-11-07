package com.amaz.dev.android.jobsaround.ui.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amaz.dev.android.jobsaround.models.DataResult
import com.amaz.dev.android.jobsaround.models.LoginResponse
import com.amaz.dev.android.jobsaround.repositories.IJobsRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(private val iJobsRepo: IJobsRepo) : ViewModel() {

    var error  = MutableLiveData<String>()

    fun login(phoneNumber: String) : LiveData<LoginResponse> {

        var data = MutableLiveData<LoginResponse>()

        viewModelScope.launch {
            when(val result = withContext(Dispatchers.IO) {iJobsRepo.login(phoneNumber)}){

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