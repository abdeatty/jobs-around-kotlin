package com.amaz.dev.android.jobsaround.ui.auth.register.seeker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amaz.dev.android.jobsaround.models.DataResult
import com.amaz.dev.android.jobsaround.models.Nationality
import com.amaz.dev.android.jobsaround.models.Qualification
import com.amaz.dev.android.jobsaround.models.SeekerRegisterRequest
import com.amaz.dev.android.jobsaround.repositories.IJobsRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SeekerRegisterViewModel(private val iJobsRepo: IJobsRepo) : ViewModel() {

    var error  = MutableLiveData<String>()

    fun seekerRegister(seekerRegisterRequest: SeekerRegisterRequest) : LiveData<Boolean> {

        var data = MutableLiveData<Boolean>()

        viewModelScope.launch {
            when(val result = withContext(IO) {iJobsRepo.seekerRegister(seekerRegisterRequest)}){

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

    fun getQualifications() : LiveData<List<Qualification>>{

        var data = MutableLiveData<List<Qualification>>()
        viewModelScope.launch {
            when(val result = withContext(IO) {iJobsRepo.getQualifications()}){

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



    fun getNationalities() : LiveData<List<Nationality>>{

        var data = MutableLiveData<List<Nationality>>()
        viewModelScope.launch {
            when(val result = withContext(IO) {iJobsRepo.getNationalities()}){

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