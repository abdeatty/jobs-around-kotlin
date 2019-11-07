package com.amaz.dev.android.jobsaround.ui.auth.register.seeker


import android.Manifest
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController

import com.amaz.dev.android.jobsaround.R
import com.amaz.dev.android.jobsaround.models.SeekerRegisterRequest
import com.blankj.utilcode.util.UriUtils
import kotlinx.android.synthetic.main.fragment_owner_register.saveButton
import kotlinx.android.synthetic.main.fragment_seeker_register.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions
import java.io.File

/**
 * A simple [Fragment] subclass.
 */

private const val RC_OPEN_DECUMENTATION = 1000
private const val GALLERY_CODE = 100
private const val DOCUMENT_CODE = 100
private const val _READ_PERMISSION_CODE = 170
class SeekerRegisterFragment : Fragment() {

    private val viewModel : SeekerRegisterViewModel by viewModel()
    private val seekerRegisterRequest by lazy { SeekerRegisterRequest() }
    private var genderType : Int ? = null
    private var englishLevel : Int ? = null
    private var english : Int ? =null
    private var jobType : Int ? =null
    private var profileImage : File? =null
    private var resume : File? =null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_seeker_register, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        genderRG.setOnCheckedChangeListener { radioGroup, i ->
            genderType = i
        }

        englishRG.setOnCheckedChangeListener{radioGroup, i ->
            english = 1
        }

        english_level_beginner_tv.setOnClickListener {
            english_level_intermediate_tv.background = null
            english_level_good_tv.background = null
            english_level_beginner_tv.background = ContextCompat.getDrawable(context!!,R.drawable.rect_light_blue)
            englishLevel = 1
        }

        english_level_intermediate_tv.setOnClickListener {
            english_level_beginner_tv.background = null
            english_level_good_tv.background = null
            english_level_intermediate_tv.background = ContextCompat.getDrawable(context!!,R.drawable.rect_light_blue)
            englishLevel = 2
        }


        english_level_good_tv.setOnClickListener {
            english_level_intermediate_tv.background = null
            english_level_beginner_tv.background = null
            english_level_good_tv.background = ContextCompat.getDrawable(context!!,R.drawable.rect_light_blue)
            englishLevel = 3
        }


        full_time_job_type_tv.setOnClickListener {
            full_time_job_type_tv.background = null
            partial_job_type_tv.background = null
            full_time_job_type_tv.background = ContextCompat.getDrawable(context!!,R.drawable.rect_light_blue)
            jobType = 2
        }
        partial_job_type_tv.setOnClickListener {
            full_time_job_type_tv.background = null
            both_partial_and_part_job_type_tv.background = null
            partial_job_type_tv.background = ContextCompat.getDrawable(context!!,R.drawable.rect_light_blue)
            jobType = 1
        }
        both_partial_and_part_job_type_tv.setOnClickListener {
            full_time_job_type_tv.background = null
            partial_job_type_tv.background = null
            both_partial_and_part_job_type_tv.background = ContextCompat.getDrawable(context!!,R.drawable.rect_light_blue)
            jobType = 1
        }

        cvTI.setOnClickListener { openFiles() }
        profilePicImage.setOnClickListener { openGallery() }


        viewModel.error.observe(this , Observer {

            it?.let {
                Toast.makeText(context,it ,Toast.LENGTH_SHORT).show()
            }
        })

        saveButton.setOnClickListener {

            if(validateInputs()){
                findNavController().navigate(R.id.action_seekerRegisterFragment_to_rulesFragment)
            }
//            viewModel.seekerRegister(seekerRegisterRequest).observe(this , Observer {
//
//                it?.let {
//                    if (it)
//                }
//            })

        }
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
                startActivityForResult(intent, 1)
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
        if (EasyPermissions.hasPermissions(requireContext(),perms)){

            val intent =  Intent()
            intent.type = "image/*"
            intent.action = (Intent.ACTION_GET_CONTENT)

            if (intent.resolveActivity(requireActivity().packageManager) != null) {

                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1)

            }
        }else{
            EasyPermissions.requestPermissions(this , "Requesting Read Permission",
                _READ_PERMISSION_CODE,perms)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {



                    data?.data?.let {
                        try {

                            profileImage = UriUtils.uri2File(it)
                            resume = UriUtils.uri2File(it)


                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }



    }
    private fun validateInputs() : Boolean{

        if (firstNameTI.text.isNullOrEmpty() || middelNameTI.text.isNullOrEmpty() || lastNameTI.text.isNullOrEmpty() || genderType == null
            || qualificationTI.text.isNullOrEmpty() || spicalizationTI.text.isNullOrEmpty() || yearsOfExperinceTI.text.isNullOrEmpty()||
            descTI.text.isNullOrEmpty() || profileImage == null || resume == null || phoneNumberTi.text.isNullOrEmpty() || emailTI.text.isNullOrEmpty()
            || english == null || genderType ==null || englishLevel == null ||qualificationTI.text.isNullOrEmpty() || spicalizationTI.text.isNullOrEmpty()){
            Toast.makeText(context,"يرجى ادخال جميع البيانات",Toast.LENGTH_SHORT).show()
            return false
        }


        seekerRegisterRequest.avatar = profileImage
        seekerRegisterRequest.resume = resume
        seekerRegisterRequest.description =  descTI.text.toString()
        seekerRegisterRequest.email = emailTI.text.toString()
        seekerRegisterRequest.english = english
        seekerRegisterRequest.firstName = firstNameTI.text.toString()
        seekerRegisterRequest.middleName = middelNameTI.text.toString()
        seekerRegisterRequest.lastName = lastNameTI.text.toString()
        seekerRegisterRequest.englishDegree = englishLevel
        seekerRegisterRequest.gender = genderType
        seekerRegisterRequest.jobType = jobType
        seekerRegisterRequest.birthdayGregorian = "1/9/2019"
        seekerRegisterRequest.birthdayHegire = "1/2/1432"
        seekerRegisterRequest.latitude = 29.23565
        seekerRegisterRequest.longitude = 32.23565
        seekerRegisterRequest.national = 1
        seekerRegisterRequest.phoneNumber = phoneNumberTi.text.toString()
        seekerRegisterRequest.qualification = qualificationTI.text.toString()
        seekerRegisterRequest.specialization = spicalizationTI.text.toString()
        seekerRegisterRequest.yearsExperience = yearsOfExperinceTI.text.toString().toInt()

        return true
    }
}
