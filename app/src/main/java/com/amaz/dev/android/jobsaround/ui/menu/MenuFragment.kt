package com.amaz.dev.android.jobsaround.ui.menu



import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle

import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment

import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

import java.util.ArrayList

import com.amaz.dev.android.jobsaround.R
import com.amaz.dev.android.jobsaround.ui.home.HomeFragment
import kotlinx.android.synthetic.main.fragment_menu.*
import kotlinx.android.synthetic.main.fragment_seeker_register.*
import kotlinx.android.synthetic.main.tool_bar.*

/**
 * A simple [Fragment] subclass.
 */
class MenuFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    private val latLngList = ArrayList<LatLng>()
    private var mGoogleMap: GoogleMap? = null

    init {
        // Required empty public constructor

        latLngList.add(LatLng(31.2116923, 29.9396))
        latLngList.add(LatLng(31.2216923, 29.9296))
        latLngList.add(LatLng(31.2136923, 29.9496))
        latLngList.add(LatLng(31.2146923, 29.9596))
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_menu, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mMapView.onCreate(savedInstanceState)
        mMapView.getMapAsync(this)

        toolBarIcon2.setImageDrawable(ContextCompat.getDrawable(context!!,R.drawable.ic_search_black_24dp))
        toolBarIcon2.setOnClickListener { findNavController().navigate(R.id.action_menuFragment_to_searchFragment) }


        job_tv.setOnClickListener {
            experience_tv.background = null
            qualifications_tv.background = null
            job_tv.background =
                ContextCompat.getDrawable(context!!, R.drawable.rect_light_blue)
        }

        experience_tv.setOnClickListener {
            qualifications_tv.background = null
            job_tv.background = null
            experience_tv.background =
                ContextCompat.getDrawable(context!!, R.drawable.rect_light_blue)
        }


        qualifications_tv.setOnClickListener {
            job_tv.background = null
            experience_tv.background = null
            qualifications_tv.background =
                ContextCompat.getDrawable(context!!, R.drawable.rect_light_blue)
        }
    }



    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        var mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY)
        if (mapViewBundle == null) {
            mapViewBundle = Bundle()
            outState.putBundle(MAPVIEW_BUNDLE_KEY, mapViewBundle)
        }

        mMapView!!.onSaveInstanceState(mapViewBundle)
    }


    override fun onMapReady(googleMap: GoogleMap) {


        mGoogleMap = googleMap
        addMarkersToMap()
        if (ActivityCompat.checkSelfPermission(activity!!, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(activity!!,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            googleMap.isMyLocationEnabled = true
            return
        }

        mGoogleMap!!.setOnMarkerClickListener(this)

    }

    private fun addMarkersToMap() {
        if (latLngList.size < 0) {
            mGoogleMap!!.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(0.0, 0.0), 12.0f))
        } else {
            for (i in latLngList.indices) {

                //                Marker marker = mGoogleMap.addMarker(
                //                        new MarkerOptions()
                //                                .position(latLngList.get(i))
                //                );
                //                marker.setTag(i + "");

                val customInfoWindowAdapter = CustomCompanyInfoWindowAdapter(context!!)
                val markerView = (context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(R.layout.map_marker_layout, null)
                val tv_marker_text = markerView.findViewById<TextView>(R.id.tv_marker_text)

                val marker = mGoogleMap!!.addMarker(MarkerOptions()
                    .position(LatLng(latLngList[i].latitude, latLngList[i].longitude)))
                marker.setIcon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(context, markerView)))


                if (i / 2 == 0)
                    marker.tag = "employee"
                else
                    marker.tag = "company"
                mGoogleMap!!.setInfoWindowAdapter(customInfoWindowAdapter)
                mGoogleMap!!.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(latLngList[i].latitude, latLngList[i].longitude), 12.0f))
            }


        }
    }

    private fun createDrawableFromView(context: Context?, view: View): Bitmap {
        val displayMetrics = DisplayMetrics()
        (context as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)
        view.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        view.measure(displayMetrics.widthPixels, displayMetrics.heightPixels)
        view.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels)
        view.buildDrawingCache()
        val bitmap = Bitmap.createBitmap(view.measuredWidth, view.measuredHeight, Bitmap.Config.ARGB_8888)

        val canvas = Canvas(bitmap)
        view.draw(canvas)

        return bitmap
    }

    override fun onResume() {
        super.onResume()
        mMapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mMapView.onPause()
    }

    override fun onStart() {
        super.onStart()
        mMapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        mMapView.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
//        mMapView.onDestroy()


    }

    override fun onLowMemory() {
        super.onLowMemory()
        mMapView.onLowMemory()
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        if (marker.tag == "company")
            mGoogleMap!!.setInfoWindowAdapter(CustomCompanyInfoWindowAdapter(context!!))
        else
            mGoogleMap!!.setInfoWindowAdapter(CustomEmployeeInfoWindowAdapter(context!!))
        return false
    }


    companion object {

        private val TAG = "MenuFragment"
        private val MAPVIEW_BUNDLE_KEY = "Map Bundle"
    }
}
