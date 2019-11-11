package com.amaz.dev.android.jobsaround.ui.map

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng

class LocationViewModel() : ViewModel(){

    var latLng = MutableLiveData<LatLng>()

    fun setLatLang(latLng: LatLng){
        this.latLng.value = latLng
    }


}