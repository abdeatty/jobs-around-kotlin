package com.amaz.dev.android.jobsaround.ui.map


import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle

import androidx.core.app.ActivityCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.amaz.dev.android.jobsaround.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.fragment_map_dialog.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


/**
 * A simple [Fragment] subclass.
 */
class MapDialogFragment : DialogFragment(), OnMapReadyCallback {

    private var mGoogleMap: GoogleMap? = null
    private lateinit var  fusedLocationClient : FusedLocationProviderClient
    private val viewModel : LocationViewModel by sharedViewModel()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_map_dialog, container, false)



        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity!!)

        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        confirmButton.setOnClickListener {

            viewModel.setLatLang(
                LatLng(
                    mGoogleMap!!.cameraPosition.target.latitude,
                    mGoogleMap!!.cameraPosition.target.longitude
                )
            )
            dismiss()
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        var mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY)
        if (mapViewBundle == null) {
            mapViewBundle = Bundle()
            outState.putBundle(MAPVIEW_BUNDLE_KEY, mapViewBundle)
        }

        mapView!!.onSaveInstanceState(mapViewBundle)
    }

    override fun onMapReady(googleMap: GoogleMap) {

        mGoogleMap = googleMap

        fusedLocationClient.lastLocation
            .addOnSuccessListener { location : Location? ->
                // Got last known location. In some rare situations this can be null.
                googleMap?.animateCamera(CameraUpdateFactory.
                    newLatLngZoom(LatLng(location?.latitude ?: 0.0 ,location?.longitude ?: 0.0), 16F))
            }

        if (ActivityCompat.checkSelfPermission(activity!!, Manifest.permission.ACCESS_FINE_LOCATION) !=
            PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                activity!!,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            googleMap.isMyLocationEnabled = true
            return
        }

        mGoogleMap!!.isMyLocationEnabled = true
        Log.e(TAG, "onMapReady: " + mGoogleMap!!.cameraPosition.target.longitude)
        Log.e(TAG, "onMapReady: " + mGoogleMap!!.cameraPosition.target.latitude)

    }

    override fun onResume() {
        super.onResume()
        mapView?.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView?.onPause()
    }

    override fun onStart() {
        super.onStart()
        mapView?.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView?.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView?.onDestroy()


    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView?.onLowMemory()
    }

    companion object {

        private val TAG = "MapDialogFragment"
        private val MAPVIEW_BUNDLE_KEY = "Map Bundle"
    }
}// Required empty public constructor
