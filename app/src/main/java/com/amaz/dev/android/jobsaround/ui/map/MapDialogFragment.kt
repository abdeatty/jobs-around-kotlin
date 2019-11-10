package com.amaz.dev.android.jobsaround.ui.map


import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle

import androidx.core.app.ActivityCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast

import com.amaz.dev.android.jobsaround.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import kotlinx.android.synthetic.main.fragment_map_dialog.*


/**
 * A simple [Fragment] subclass.
 */
class MapDialogFragment : DialogFragment(), OnMapReadyCallback, View.OnClickListener {

    private var mGoogleMap: GoogleMap? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_map_dialog, container, false)


//        initGoogleMap(savedInstanceState)

        val mapFragment = activity?.supportFragmentManager
            ?.findFragmentById(R.id.mapView) as SupportMapFragment
        mapFragment.getMapAsync(this)



        //        getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        return view
    }

    private fun initGoogleMap(savedInstanceState: Bundle?) {
        var mapViewBundle: Bundle? = null
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY)
        }
        mapView!!.onCreate(mapViewBundle)
        mapView!!.getMapAsync(this)
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
        mapView!!.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView!!.onPause()
    }

    override fun onStart() {
        super.onStart()
        mapView!!.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView!!.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView!!.onDestroy()


    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView!!.onLowMemory()
    }

    override fun onClick(view: View) {

        when (view.id) {
            R.id.confirmButton -> {
                Toast.makeText(context, mGoogleMap!!.cameraPosition.target.longitude.toString() + "", Toast.LENGTH_SHORT).show()
                Toast.makeText(context, mGoogleMap!!.cameraPosition.target.latitude.toString() + "", Toast.LENGTH_SHORT).show()
                dismiss()
            }
        }
    }

    companion object {

        private val TAG = "MapDialogFragment"
        private val MAPVIEW_BUNDLE_KEY = "Map Bundle"
    }
}// Required empty public constructor
