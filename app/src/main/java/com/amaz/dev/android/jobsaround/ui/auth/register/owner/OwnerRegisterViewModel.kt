package com.amaz.dev.android.jobsaround.ui.auth.register.owner

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amaz.dev.android.jobsaround.models.OwnerRegisterRequest
import com.amaz.dev.android.jobsaround.repositories.IJobsRepo
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.amaz.dev.android.jobsaround.models.DataResult

class OwnerRegisterViewModel(private val iJobsRepo: IJobsRepo) : ViewModel() {

    var error  = MutableLiveData<String>()

    fun registerOwner(ownerRegisterRequest: OwnerRegisterRequest) : LiveData<Boolean>{

        var data = MutableLiveData<Boolean>()

        viewModelScope.launch {
            when(val result = withContext(IO) {iJobsRepo.ownerRegister(ownerRegisterRequest)}){

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