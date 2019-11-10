package com.amaz.dev.android.jobsaround.ui.menu

import android.content.Context
import android.view.LayoutInflater
import android.view.View


import com.amaz.dev.android.jobsaround.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker

class CustomEmployeeInfoWindowAdapter(private val mContext: Context) : GoogleMap.InfoWindowAdapter {

    private val mWindow: View

    init {
        mWindow = LayoutInflater.from(mContext)
            .inflate(R.layout.employee_marker_info_window_content_layout, null)
    }

    override fun getInfoWindow(marker: Marker): View? {
        return null
    }

    override fun getInfoContents(marker: Marker): View {
        return mWindow
    }
}
