package com.amaz.dev.android.jobsaround.ui.createJob


import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.SyncStateContract
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer

import com.amaz.dev.android.jobsaround.R
import com.amaz.dev.android.jobsaround.models.CreateJobRequest
import com.amaz.dev.android.jobsaround.ui.map.LocationViewModel
import com.amaz.dev.android.jobsaround.ui.map.MapDialogFragment
import kotlinx.android.synthetic.main.fragment_create_job.*
import kotlinx.android.synthetic.main.fragment_create_job.englishLevelBeginnerTV
import kotlinx.android.synthetic.main.fragment_create_job.englishLevelGoodTV
import kotlinx.android.synthetic.main.fragment_create_job.englishLevelIntermediateTV
import kotlinx.android.synthetic.main.fragment_create_job.englishRG
import kotlinx.android.synthetic.main.fragment_create_job.genderRG
import kotlinx.android.synthetic.main.fragment_create_job.qualificationTV
import kotlinx.android.synthetic.main.fragment_profile.createJobButton
import kotlinx.android.synthetic.main.tool_bar.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */

private const val LOCATION_PERMISSION_REQUEST_CODE = 65
class CreateJobFragment : Fragment() {

    private val viewModel: CreateJobViewModel by viewModel()
    private val locationViewModel: LocationViewModel by viewModel()
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


        mapImgView.setOnClickListener { openMapFragment() }

        locationViewModel.latLng.observe(this, Observer {
            it?.let {
                latitude = it.latitude
                longitude = it.longitude
            }
        })

        genderRG.setOnCheckedChangeListener { radioGroup, i ->

            var selectedButtonId = radioGroup.checkedRadioButtonId
            var radioButton = radioGroup.findViewById<RadioButton>(selectedButtonId)
            var radioButtonIndex = radioGroup.indexOfChild(radioButton)
            gender = radioButtonIndex
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
        viewModel.getQualifications().observe(this, Observer {
            it?.let {
                val adapter = ArrayAdapter<String>(
                    context!!,
                    R.layout.drop_down_item,
                    it.map { it.qualification }
                )
                qualificationTV.setAdapter(adapter)
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

        if (jobTitleEt.text.isNullOrEmpty() || jobDescEt.text.isNullOrEmpty() || gender == null
            || yearsOfExperienceET.text.isNullOrEmpty() || english == null || englishLevel == null || jobType == null
        ) {

            Toast.makeText(context, "يرجى ادخال جميع البيانات", Toast.LENGTH_LONG).show()
            return false
        }



        createJobRequest.jobTitle = jobTitleEt.text.toString()
        createJobRequest.buildLogo = 1
        createJobRequest.buildName = 1
        createJobRequest.description = jobDescEt.text.toString()
        createJobRequest.english = english
        createJobRequest.englishDegree = englishLevel
        createJobRequest.experience = yearsOfExperienceET.text.toString().toInt()
        createJobRequest.gender = gender
        createJobRequest.latitude = latitude
        createJobRequest.longitude = longitude
        createJobRequest.national = 1
        createJobRequest.jobType = jobType
        createJobRequest.qualification = 1
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
}
