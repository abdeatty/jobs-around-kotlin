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
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView

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
        initGoogleMap(savedInstanceState)
        return view
    }


    private fun initGoogleMap(savedInstanceState: Bundle?) {
        var mapViewBundle: Bundle? = null
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY)
        }
        mMapView!!.onCreate(mapViewBundle)
        mMapView!!.getMapAsync(this)
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
            googleMap.isMyLocationEnabled = true
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
                //                        mGoogleMap.setInfoWindowAdapter(customInfoWindowAdapter);
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
        mMapView!!.onResume()
    }

    override fun onPause() {
        super.onPause()
        mMapView!!.onPause()
    }

    override fun onStart() {
        super.onStart()
        mMapView!!.onStart()
    }

    override fun onStop() {
        super.onStop()
        mMapView!!.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mMapView!!.onDestroy()


    }

    override fun onLowMemory() {
        super.onLowMemory()
        mMapView!!.onLowMemory()
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        if (marker.tag == "company")
            mGoogleMap!!.setInfoWindowAdapter(CustomCompanyInfoWindowAdapter(context!!))
        else
            mGoogleMap!!.setInfoWindowAdapter(CustomEmployeeInfoWindowAdapter(context!!))
        return false
    }

//    @OnClick(R.id.experience_tv)
//    fun onExperienceTextViewClicked() {
//        jobTv!!.background = null
//        qualificationsTv!!.background = null
//        experienceTv!!.background = resources.getDrawable(R.drawable.rect_light_blue)
//    }
//
//    @OnClick(R.id.qualifications_tv)
//    fun onQualificationsTextViewClicked() {
//        jobTv!!.background = null
//        experienceTv!!.background = null
//        qualificationsTv!!.background = resources.getDrawable(R.drawable.rect_light_blue)
//    }
//
//
//    @OnClick(R.id.job_tv)
//    fun onJobTextViewClicked() {
//        experienceTv!!.background = null
//        qualificationsTv!!.background = null
//        jobTv!!.background = resources.getDrawable(R.drawable.rect_light_blue)
//    }

    companion object {

        private val TAG = "MenuFragment"
        private val MAPVIEW_BUNDLE_KEY = "Map Bundle"
    }
}
