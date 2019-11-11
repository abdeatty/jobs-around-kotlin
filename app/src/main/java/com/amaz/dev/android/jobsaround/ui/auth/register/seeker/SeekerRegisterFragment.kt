package com.amaz.dev.android.jobsaround.ui.auth.register.seeker


import android.Manifest
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import java.util.Calendar
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController

import com.amaz.dev.android.jobsaround.R
import com.amaz.dev.android.jobsaround.models.SeekerRegisterRequest
import com.amaz.dev.android.jobsaround.ui.map.LocationViewModel
import com.amaz.dev.android.jobsaround.ui.map.MapDialogFragment
import com.blankj.utilcode.util.UriUtils
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_owner_register.saveButton
import kotlinx.android.synthetic.main.fragment_seeker_register.*
import kotlinx.android.synthetic.main.tool_bar.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions
import java.io.File


/**
 * A simple [Fragment] subclass.
 */

private const val RC_OPEN_DECUMENTATION = 1000
private const val GALLERY_CODE = 100
private const val DOCUMENT_CODE = 110
private const val _READ_PERMISSION_CODE = 170
private const val LOCATION_PERMISSION_REQUEST_CODE = 1

class SeekerRegisterFragment : Fragment()  {



    private val viewModel: SeekerRegisterViewModel by viewModel()
    private val locationViewModel : LocationViewModel by sharedViewModel()
    private val seekerRegisterRequest by lazy { SeekerRegisterRequest() }
    private var genderType: Int? = null
    private var englishLevel: Int? = null
    private var english: Int? = null
    private var jobType: Int? = null
    private var profileImageFile: File? = null
    private var resume: File? = null
    private var latitiude : Double = 0.0
    private var longitude : Double  =0.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_seeker_register, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        toolBarIcon2.setImageDrawable(ContextCompat.getDrawable(context!!,R.drawable.ic_arrow_point_to_right))
        toolBarIcon2.setOnClickListener { activity?.onBackPressed() }
        appBarTitle.text = getString(R.string.seeker_info)
        

        mapImgV.setOnClickListener { openMapFragment() }

        hjrigDateTV.setOnClickListener { openCalender(hjrigDateTV) }
        gregorianDateTV.setOnClickListener {openCalender(gregorianDateTV)}
        genderRG.setOnCheckedChangeListener { radioGroup, i -> (
                if (i%2 == 0) genderType = 2
                        else genderType = 1
                ) }

        englishRG.setOnCheckedChangeListener { radioGroup, i ->
            english = 1
        }

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


        full_time_job_type_tv.setOnClickListener {
            full_time_job_type_tv.background = null
            partial_job_type_tv.background = null
            full_time_job_type_tv.background =
                ContextCompat.getDrawable(context!!, R.drawable.rect_light_blue)
            jobType = 2
        }
        partial_job_type_tv.setOnClickListener {
            full_time_job_type_tv.background = null
            both_partial_and_part_job_type_tv.background = null
            partial_job_type_tv.background =
                ContextCompat.getDrawable(context!!, R.drawable.rect_light_blue)
            jobType = 1
        }
        both_partial_and_part_job_type_tv.setOnClickListener {
            full_time_job_type_tv.background = null
            partial_job_type_tv.background = null
            both_partial_and_part_job_type_tv.background =
                ContextCompat.getDrawable(context!!, R.drawable.rect_light_blue)
            jobType = 1
        }

        cvTI.setOnClickListener { openFiles() }
        profilePicImage.setOnClickListener { openGallery() }


        locationViewModel.latLng.observe(this , Observer {
            latitiude = it.latitude
            longitude = it.longitude
            Toast.makeText(context,"${it.latitude}",Toast.LENGTH_LONG).show()
        })
        viewModel.error.observe(this, Observer {

            it?.let {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.getQualifications().observe(this , Observer {

            it?.let {

                val adapter = ArrayAdapter<String>(
                    context!!,
                    R.layout.drop_down_item,
                    it.map { it.qualification }
                )

                qualificationTV.setAdapter(adapter)
            }
        })



        viewModel.getNationalities().observe(this , Observer {

            it?.let {

                val adapter = ArrayAdapter<String>(
                    context!!,
                    R.layout.drop_down_item,
                    it.map { it.national }
                )

                nationalityTV.setAdapter(adapter)
            }
        })
        val yearsOfExperience = arrayListOf("1","2","3","4","5","6")
        val adapter = ArrayAdapter<String>(context!!,R.layout.drop_down_item,yearsOfExperience)
        yearsOfExperienceTV.setAdapter(adapter)
        saveButton.setOnClickListener {

            if (validateInputs()) {


                viewModel.seekerRegister(seekerRegisterRequest).observe(this, Observer {

                    it?.let {
                        if (it) findNavController().navigate(R.id.action_seekerRegisterFragment_to_rulesFragment)
                    }
                })
            }

        }
    }


    private fun openCalender(tv : TextView) {


        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)


        val dpd = DatePickerDialog(context!!, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

           tv.text  =  "$year/$monthOfYear/$dayOfMonth"

        }, year, month, day)

        dpd.show()

    }
    private fun openMapFragment(){

        if (ActivityCompat.checkSelfPermission(context!!,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity!!,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
            return
        }
        val transaction = activity?.supportFragmentManager?.beginTransaction()

        var mapFragment = activity?.supportFragmentManager?.findFragmentByTag("map_fragment")
        if (mapFragment != null) transaction?.remove(mapFragment)
        transaction?.addToBackStack(null)
        var dialogMapFragment =  MapDialogFragment()
        dialogMapFragment.show(transaction!! , "map_fragment")
    }

    @AfterPermissionGranted(RC_OPEN_DECUMENTATION)
    private fun openFiles() {
        val perms = Manifest.permission.READ_EXTERNAL_STORAGE
        if (EasyPermissions.hasPermissions(requireContext(), perms)) {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.type = "*/*"
            val mimetypes = arrayOf("image/*", "application/pdf")
            intent.putExtra(Intent.EXTRA_MIME_TYPES, mimetypes)
            intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true)
            if (intent.resolveActivity(requireActivity().packageManager) != null) {
                startActivityForResult(intent, DOCUMENT_CODE)
            }
        } else {
            EasyPermissions.requestPermissions(
                this,
                "Requesting read permission",
                RC_OPEN_DECUMENTATION,
                perms
            )
        }
    }


    private fun openGallery() {

        val perms = Manifest.permission.READ_EXTERNAL_STORAGE
        if (EasyPermissions.hasPermissions(requireContext(), perms)) {

            val intent = Intent()
            intent.type = "image/*"
            intent.action = (Intent.ACTION_GET_CONTENT)

            if (intent.resolveActivity(requireActivity().packageManager) != null) {

                startActivityForResult(Intent.createChooser(intent, "Select Picture"), GALLERY_CODE)

            }
        } else {
            EasyPermissions.requestPermissions(
                this, "Requesting Read Permission",
                _READ_PERMISSION_CODE, perms
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {


            Log.e("CODE" , "$requestCode")
            when(requestCode){
                DOCUMENT_CODE -> {
                    data?.data?.let {
                        try {

                            resume = UriUtils.uri2File(it)
                            cvTI.setText(resume?.absolutePath.toString())


                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
                else  -> {
                    data?.data?.let {
                        try {

                            profileImageFile = UriUtils.uri2File(it)
                            Glide.with(context!!).load(profileImageFile).into(profilePicImage)


                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
            }

        }


    }

//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//
//    }
    private fun validateInputs(): Boolean {

        if (firstNameTI.text.isNullOrEmpty() || middelNameTI.text.isNullOrEmpty() || lastNameTI.text.isNullOrEmpty() || genderType == null
            || qualificationTV.text.isNullOrEmpty() || spicalizationTI.text.isNullOrEmpty() || yearsOfExperienceTV.text.isNullOrEmpty() ||
            descTI.text.isNullOrEmpty() || profileImageFile == null || resume == null || phoneNumberTi.text.isNullOrEmpty() || emailTI.text.isNullOrEmpty()
            || english == null || genderType == null || englishLevel == null || spicalizationTI.text.isNullOrEmpty()
        ) {
            Toast.makeText(context, "يرجى ادخال جميع البيانات", Toast.LENGTH_SHORT).show()
            return false
        }


        seekerRegisterRequest.avatar = profileImageFile
        seekerRegisterRequest.resume = resume
        seekerRegisterRequest.description = descTI.text.toString()
        seekerRegisterRequest.email = emailTI.text.toString()
        seekerRegisterRequest.english = english
        seekerRegisterRequest.firstName = firstNameTI.text.toString()
        seekerRegisterRequest.middleName = middelNameTI.text.toString()
        seekerRegisterRequest.lastName = lastNameTI.text.toString()
        seekerRegisterRequest.englishDegree = englishLevel
        seekerRegisterRequest.gender = genderType
        seekerRegisterRequest.jobType = jobType
        seekerRegisterRequest.birthdayGregorian = gregorianDateTV.text.toString()
        seekerRegisterRequest.birthdayHegire = hjrigDateTV.text.toString()
        seekerRegisterRequest.latitude = latitiude
        seekerRegisterRequest.longitude = longitude
        seekerRegisterRequest.national = 1
        seekerRegisterRequest.phoneNumber = phoneNumberTi.text.toString()
        seekerRegisterRequest.qualification = qualificationTV.text.toString()
        seekerRegisterRequest.specialization = spicalizationTI.text.toString()
        seekerRegisterRequest.yearsExperience = yearsOfExperienceTV.text.toString().toInt()

        return true
    }


}


