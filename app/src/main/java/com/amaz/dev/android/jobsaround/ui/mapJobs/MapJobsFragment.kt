package com.amaz.dev.android.jobsaround.ui.mapJobs



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
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

import com.amaz.dev.android.jobsaround.R
import com.amaz.dev.android.jobsaround.models.JobDetails
import com.amaz.dev.android.jobsaround.ui.menuJobs.MenuJobsViewModel
import kotlinx.android.synthetic.main.fragment_map_jobs.*
import kotlinx.android.synthetic.main.map_marker_layout.*
import kotlinx.android.synthetic.main.tool_bar.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * A simple [Fragment] subclass.
 */
class MapJobsFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    private lateinit var mJobList : List<JobDetails>
    private var mGoogleMap: GoogleMap? = null

    private val viewModel : MenuJobsViewModel by sharedViewModel()



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_map_jobs, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mMapView.onCreate(savedInstanceState)
        mMapView.getMapAsync(this)

        toolBarIcon2.setImageDrawable(ContextCompat.getDrawable(context!!,R.drawable.ic_search_black_24dp))
        toolBarIcon2.setOnClickListener { findNavController().navigate(R.id.action_mapJobsFragment_to_searchFragment) }


        jobFilterTV.setOnClickListener {
            experienceFilterTV.background = null
            qualificationsFilterTV.background = null
            jobFilterTV.background =
                ContextCompat.getDrawable(context!!, R.drawable.rect_light_blue)
        }

        experienceFilterTV.setOnClickListener {
            qualificationsFilterTV.background = null
            jobFilterTV.background = null
            experienceFilterTV.background =
                ContextCompat.getDrawable(context!!, R.drawable.rect_light_blue)
        }


        qualificationsFilterTV.setOnClickListener {
            jobFilterTV.background = null
            experienceFilterTV.background = null
            qualificationsFilterTV.background =
                ContextCompat.getDrawable(context!!, R.drawable.rect_light_blue)
        }


        viewModel.jobList.observe(this , Observer {

            it?.let{
                mJobList = it
            }
        })
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
        if (mJobList.size < 0) {
            mGoogleMap!!.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(0.0, 0.0), 12.0f))
        } else {
            for (job in mJobList) {


                val customInfoWindowAdapter = OwnerInfoWindowAdapter(context!!)
                val markerView = (context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(R.layout.map_marker_layout, null)


                var markerContentTV = markerView.findViewById<TextView>(R.id.markerContentTV)
                markerContentTV.text = job.jobTitle
                var marker = mGoogleMap!!.addMarker(MarkerOptions()
                    .position(LatLng(job.latitude!!.toDouble() , job.longitude!!.toDouble())))

                if (job.gender == 2){
                    markerView.setBackgroundResource(R.color.pink)
                }
                marker.setIcon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(context, markerView)))


                mGoogleMap!!.setInfoWindowAdapter(customInfoWindowAdapter)
                mGoogleMap!!.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(job.latitude!!.toDouble(), job.longitude!!.toDouble()), 12.0f))
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
        mMapView?.onResume()
    }

    override fun onPause() {
        super.onPause()
        mMapView?.onPause()
    }

    override fun onStart() {
        super.onStart()
        mMapView?.onStart()
    }

    override fun onStop() {
        super.onStop()
        mMapView?.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mMapView?.onDestroy()


    }

    override fun onLowMemory() {
        super.onLowMemory()
        mMapView?.onLowMemory()
    }

    override fun onMarkerClick(marker: Marker): Boolean {

        mGoogleMap!!.setInfoWindowAdapter(OwnerInfoWindowAdapter(context!!))
//        if (marker.tag == "company")
//            mGoogleMap!!.setInfoWindowAdapter(OwnerInfoWindowAdapter(context!!))
//        else
//            mGoogleMap!!.setInfoWindowAdapter(SeekerInfoWindowAdapter(context!!))
        return false
    }


    companion object {

        private val TAG = "MapJobsFragment"
        private val MAPVIEW_BUNDLE_KEY = "Map Bundle"
    }
}
