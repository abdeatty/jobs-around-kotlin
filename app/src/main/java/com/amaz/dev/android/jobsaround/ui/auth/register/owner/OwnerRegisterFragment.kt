package com.amaz.dev.android.jobsaround.ui.auth.register.owner


import android.Manifest
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController

import com.amaz.dev.android.jobsaround.R
import com.amaz.dev.android.jobsaround.models.OwnerRegisterRequest
import com.blankj.utilcode.util.UriUtils
import kotlinx.android.synthetic.main.fragment_owner_register.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions
import java.io.File

private const val RC_OPEN_DECUMENTATION = 1000
private const val GALLERY_CODE = 100
private const val DOCUMENT_CODE = 100
private const val _READ_PERMISSION_CODE = 170


/**
 * A simple [Fragment] subclass.
 */
class OwnerRegisterFragment : Fragment() {

    private val viewModel : OwnerRegisterViewModel by viewModel()
    private val ownerRegisterRequest by lazy { OwnerRegisterRequest()}
    private  var organizationImage : File ? =null
    private  var businessCommercialFile : File ? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_owner_register, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        organizationImageTI.setOnClickListener { openGallery() }
        businessCommercialImageTIET.setOnClickListener { openFiles() }
        viewModel.error.observe(this , Observer {
            it?.let {
                Toast.makeText(context ,it,Toast.LENGTH_SHORT).show()
            }
        })


        saveButton.setOnClickListener {
            if (validateInputs()){
                viewModel.registerOwner(ownerRegisterRequest).observe(this, Observer {

                    it?.let {
                        if (it) Toast.makeText(context,"تم التسجيل بنجاح",Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_ownerRegisterFragment_to_rulesFragment)
                    }
                })
            }


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
        if (EasyPermissions.hasPermissions(requireContext(),perms)){

            val intent =  Intent()
            intent.type = "image/*"
            intent.action = (Intent.ACTION_GET_CONTENT)

            if (intent.resolveActivity(requireActivity().packageManager) != null) {

                startActivityForResult(Intent.createChooser(intent, "Select Picture"),GALLERY_CODE)

            }
        }else{
            EasyPermissions.requestPermissions(this , "Requesting Read Permission",_READ_PERMISSION_CODE,perms)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when(requestCode){
                 GALLERY_CODE -> {

                     data?.data?.let {
                         try {


                             organizationImage = UriUtils.uri2File(it)
                             businessCommercialFile = UriUtils.uri2File(it)
                             organizationImage?.name?.let { fileName ->
                                 organizationImageTI.setText(fileName)
                             }
                         } catch (e: Exception) {
                             e.printStackTrace()
                         }
                     }
                }

                DOCUMENT_CODE -> {

                    data?.data?.let {
                        try {
                            businessCommercialFile = UriUtils.uri2File(it)
                            businessCommercialFile?.name?.let { fileName ->
                                businessCommercialImageTIET.setText(fileName)
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
            }

        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    private fun validateInputs() : Boolean{

        if (organizationNameTIET.text.isNullOrEmpty()){
            Toast.makeText(context,"يرجى ادخال جميع البيانات",Toast.LENGTH_SHORT).show()
            return false
        }
        if (organizationActivityTI.text.isNullOrEmpty()){
            Toast.makeText(context,"يرجى ادخال جميع البيانات",Toast.LENGTH_SHORT).show()
            return false
        }
        if (authorizedPersonNameTIET.text.isNullOrEmpty()){
            Toast.makeText(context,"يرجى ادخال جميع البيانات",Toast.LENGTH_SHORT).show()
            return false
        }
        if (businessCommercialNumberTIET.text.isNullOrEmpty()){
            Toast.makeText(context,"يرجى ادخال جميع البيانات",Toast.LENGTH_SHORT).show()
            return false
        }
        if (phoneNumberTIET.text.isNullOrEmpty()){
            Toast.makeText(context,"يرجى ادخال جميع البيانات",Toast.LENGTH_SHORT).show()
            return false
        }
        if (phoneTIET.text.isNullOrEmpty()){
            Toast.makeText(context,"يرجى ادخال جميع البيانات",Toast.LENGTH_SHORT).show()
            return false
        }

        if (emailTIET.text.isNullOrEmpty() || businessCommercialFile == null || organizationImage == null){
            Toast.makeText(context,"يرجى ادخال جميع البيانات",Toast.LENGTH_SHORT).show()
            return false
        }



        ownerRegisterRequest.activity = organizationActivityTI.text.toString()
        ownerRegisterRequest.buildName = organizationNameTIET.text.toString()
        ownerRegisterRequest.email = emailTIET.text.toString()
        ownerRegisterRequest.icon = organizationImage
        ownerRegisterRequest.latitude = 29.325655
        ownerRegisterRequest.longitude = 32.23659
        ownerRegisterRequest.registerImage = businessCommercialFile
        ownerRegisterRequest.phone = phoneTIET.text.toString()
        ownerRegisterRequest.phoneNumber = phoneNumberTIET.text.toString()
        ownerRegisterRequest.registrationNumber = businessCommercialNumberTIET.text.toString()
        ownerRegisterRequest.username = authorizedPersonNameTIET.text.toString()
        return  true
    }
}
