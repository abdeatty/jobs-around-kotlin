package com.amaz.dev.android.jobsaround.ui.createJob


import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer

import com.amaz.dev.android.jobsaround.R
import com.amaz.dev.android.jobsaround.models.*
import com.amaz.dev.android.jobsaround.ui.map.LocationViewModel
import com.amaz.dev.android.jobsaround.ui.map.MapDialogFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.fragment_create_job.*
import kotlinx.android.synthetic.main.fragment_create_job.englishLevelBeginnerTV
import kotlinx.android.synthetic.main.fragment_create_job.englishLevelGoodTV
import kotlinx.android.synthetic.main.fragment_create_job.englishLevelIntermediateTV
import kotlinx.android.synthetic.main.fragment_create_job.englishRG
import kotlinx.android.synthetic.main.fragment_create_job.genderRG
import kotlinx.android.synthetic.main.fragment_create_job.mapView
import kotlinx.android.synthetic.main.fragment_create_job.qualificationTV
import kotlinx.android.synthetic.main.fragment_profile.createJobButton
import kotlinx.android.synthetic.main.tool_bar.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */

private var qualificationsID : Int ? = null
private var yearOfExperienceID: Int? = null
private lateinit var qualificationList : List<Qualification>
private lateinit var yearOfExperienceList : List<ExperienceYears>
private const val LOCATION_PERMISSION_REQUEST_CODE = 65
private lateinit var mGoogleMap : GoogleMap

class CreateJobFragment : Fragment() , OnMapReadyCallback, GoogleMap.OnMapClickListener {
    override fun onMapClick(p0: LatLng?) {
        openMapFragment()
    }

    override fun onMapReady(googleMap: GoogleMap?) {

        mGoogleMap = googleMap!!
        mGoogleMap?.setOnMapClickListener(this)
    }



    private val viewModel: CreateJobViewModel by viewModel()
    private val locationViewModel: LocationViewModel by sharedViewModel()
    private val createJobRequest by lazy { CreateJobRequest() }
    private var latitude: Double = 0.0
    private var longitude: Double = 0.0
    private var english: Int? = null
    private var englishLevel: Int? = null
    private var jobType: Int? = null
    private var gender: Int? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_job, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        toolBarIcon2.setImageDrawable(
            ContextCompat.getDrawable(
                context!!,
                R.drawable.ic_arrow_point_to_right
            )
        )
        toolBarIcon2.setOnClickListener { activity?.onBackPressed() }
        appBarTitle.text = getString(R.string.add_job)




        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        locationViewModel.latLng.observe(this, Observer {
            it?.let {
                latitude = it.latitude
                longitude = it.longitude
                mGoogleMap.animateCamera(
                    CameraUpdateFactory.newLatLngZoom(LatLng(latitude , longitude), 16F))
            }
        })

        genderRG.setOnCheckedChangeListener { radioGroup, i ->

            var selectedButtonId = radioGroup.checkedRadioButtonId
            var radioButton = radioGroup.findViewById<RadioButton>(selectedButtonId)
            var radioButtonIndex = radioGroup.indexOfChild(radioButton)
            gender = radioButtonIndex + 1
        }
        englishRG.setOnCheckedChangeListener { radioGroup, i -> english = i }


        englishLevelBeginnerTV.setOnClickListener {
            englishLevelIntermediateTV.background = null
            englishLevelGoodTV.background = null
            englishLevelBeginnerTV.background =
                ContextCompat.getDrawable(context!!, R.drawable.rect_light_blue)
            englishLevel = 1
        }

        englishLevelIntermediateTV.setOnClickListener {
            englishLevelBeginnerTV.background = null
            englishLevelGoodTV.background = null
            englishLevelIntermediateTV.background =
                ContextCompat.getDrawable(context!!, R.drawable.rect_light_blue)
            englishLevel = 2
        }


        englishLevelGoodTV.setOnClickListener {
            englishLevelIntermediateTV.background = null
            englishLevelBeginnerTV.background = null
            englishLevelGoodTV.background =
                ContextCompat.getDrawable(context!!, R.drawable.rect_light_blue)
            englishLevel = 3
        }

        fullTimeJobTV.setOnClickListener {
            partTimeJobTV.background = null
            fullTimeJobTV.background =
                ContextCompat.getDrawable(context!!, R.drawable.rect_light_blue)
            jobType = 2
        }


        partTimeJobTV.setOnClickListener {
            fullTimeJobTV.background = null
            partTimeJobTV.background =
                ContextCompat.getDrawable(context!!, R.drawable.rect_light_blue)
            jobType = 1
        }

        viewModel.getExperienceYears().observe(this , Observer {

            it?.let{
                yearOfExperienceList = it
                val adapter = ArrayAdapter<String>(
                    context!!,
                    R.layout.drop_down_item,
                    it.map { it.years })
                yearsOfExperienceTV.setAdapter(adapter)
                yearsOfExperienceTV.onItemClickListener = ItemClickListener(yearsOfExperienceTV)
            }
        })


        viewModel.getQualifications().observe(this, Observer {
            it?.let {
                qualificationList = it
                val adapter = ArrayAdapter<String>(
                    context!!,
                    R.layout.drop_down_item,
                    it.map { it.qualification }
                )
                qualificationTV.setAdapter(adapter)
                qualificationTV.onItemClickListener = ItemClickListener(qualificationTV)
            }
        })
        viewModel.error.observe(this, Observer {
            it?.let {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }
        })

        createJobButton.setOnClickListener {

            if (validateInputs()) {

                viewModel.createJobForOwner(createJobRequest).observe(this, Observer {
                    Toast.makeText(context, "Success", Toast.LENGTH_LONG).show()
                })
            }
        }
    }


    private fun validateInputs(): Boolean {


        if (jobTitleEt.text.isNullOrEmpty() || jobDescEt.text.isNullOrEmpty() || gender == null ||  yearOfExperienceID == null
            || yearsOfExperienceTV.text.isNullOrEmpty() || english == null || englishLevel == null || jobType == null || qualificationsID == null
        ) {

            Toast.makeText(context, "يرجى ادخال جميع البيانات", Toast.LENGTH_LONG).show()
            return false
        }


        createJobRequest.jobTitle = jobTitleEt.text.toString()
        createJobRequest.buildLogo = if(hideOrganizationLogoSw.isChecked)  1 else 0
        createJobRequest.buildName = if(hideOrganizationNameSw.isChecked)  1 else 0
        createJobRequest.description = jobDescEt.text.toString()
        createJobRequest.english = english
        createJobRequest.englishDegree = englishLevel
        createJobRequest.experience = yearOfExperienceID
        createJobRequest.gender = gender
        createJobRequest.latitude = latitude
        createJobRequest.longitude = longitude
        createJobRequest.national = 1
        createJobRequest.jobType = jobType
        createJobRequest.qualification = qualificationsID
        return true
    }

    private fun openMapFragment(){

        if (ActivityCompat.checkSelfPermission(context!!,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity!!,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE
            )
            return
        }
        val transaction = activity?.supportFragmentManager?.beginTransaction()

        var mapFragment = activity?.supportFragmentManager?.findFragmentByTag("map_fragment")
        if (mapFragment != null) transaction?.remove(mapFragment)
        transaction?.addToBackStack(null)
        var dialogMapFragment =  MapDialogFragment()
        dialogMapFragment.show(transaction!! , "map_fragment")
    }



    class ItemClickListener(private val ac: AutoCompleteTextView) :
        AdapterView.OnItemClickListener {


        override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {


            when (ac.id) {

                R.id.qualificationTV ->  qualificationsID = qualificationList[p2].id
                R.id.yearsOfExperienceTV -> yearOfExperienceID = yearOfExperienceList[p2].id

            }
        }



    }


    override fun onStart() {
        super.onStart()
        mapView?.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView?.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView?.onPause()
        locationViewModel.latLng.value = null
    }

    override fun onStop() {
        super.onStop()
        mapView?.onStop()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView?.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView?.onDestroy()
    }
}
