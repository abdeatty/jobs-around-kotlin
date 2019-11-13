package com.amaz.dev.android.jobsaround.ui.auth.register.owner


import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController

import com.amaz.dev.android.jobsaround.R
import com.amaz.dev.android.jobsaround.models.OwnerRegisterRequest
import com.amaz.dev.android.jobsaround.ui.map.LocationViewModel
import com.amaz.dev.android.jobsaround.ui.map.MapDialogFragment
import com.blankj.utilcode.util.UriUtils
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.fragment_owner_register.*
import kotlinx.android.synthetic.main.fragment_owner_register.saveButton
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
private const val DOCUMENT_CODE = 101
private const val _READ_PERMISSION_CODE = 170
private const val LOCATION_PERMISSION_REQUEST_CODE = 1
class OwnerRegisterFragment : Fragment(), OnMapReadyCallback ,GoogleMap.OnMapClickListener {
    override fun onMapClick(p0: LatLng?) {
        openMapFragment()
    }

    override fun onMapReady(googleMap: GoogleMap?) {

        mGoogleMap = googleMap!!
        googleMap?.setOnMapClickListener(this)
    }


    private lateinit var mGoogleMap : GoogleMap
    private val viewModel : OwnerRegisterViewModel by viewModel()
    private val locationViewModel : LocationViewModel by sharedViewModel()
    private val ownerRegisterRequest by lazy { OwnerRegisterRequest()}
    private  var organizationImage : File ? =null
    private  var businessCommercialFile : File ? = null
    private var latitiude : Double = 0.0
    private var longitude : Double  =0.0



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_owner_register, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        appBarTitle.text = getString(R.string.foundation_info)
        toolBarIcon2.setImageDrawable(ContextCompat.getDrawable(context!!,R.drawable.ic_arrow_point_to_right))
        toolBarIcon2.setOnClickListener { activity?.onBackPressed() }


        organizationImageTI.setOnClickListener { openGallery() }

        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        businessCommercialImageTIET.setOnClickListener { openFiles() }
        viewModel.error.observe(this , Observer {
            it?.let {
                Toast.makeText(context ,it,Toast.LENGTH_SHORT).show()
            }
        })

        locationViewModel.latLng.observe(this , Observer {

            it?.let {
                latitiude = it.latitude
                longitude = it.longitude
                mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(latitiude , longitude), 16F))
            }


        })


        saveButton.setOnClickListener {
            findNavController().navigate(R.id.action_ownerRegisterFragment_to_homeFragment)
            if (validateInputs()){
                viewModel.registerOwner(ownerRegisterRequest).observe(this, Observer {

                    it?.let {
                        if (it) Toast.makeText(context,"تم التسجيل بنجاح",Toast.LENGTH_SHORT).show()
//                        findNavController().navigate(R.id.action_ownerRegisterFragment_to_rulesFragment)
                    }
                })
            }


        }
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
                             organizationImageTI.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_correct, 0, 0, 0);
                         } catch (e: Exception) {
                             e.printStackTrace()
                         }
                     }
                }

                DOCUMENT_CODE -> {

                    data?.data?.let {
                        try {
                            businessCommercialFile = UriUtils.uri2File(it)
                            businessCommercialImageTIET.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_correct, 0, 0, 0);
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
        ownerRegisterRequest.latitude = latitiude
        ownerRegisterRequest.longitude = longitude
        ownerRegisterRequest.registerImage = businessCommercialFile
        ownerRegisterRequest.phone = phoneTIET.text.toString()
        ownerRegisterRequest.phoneNumber = phoneNumberTIET.text.toString()
        ownerRegisterRequest.registrationNumber = businessCommercialNumberTIET.text.toString()
        ownerRegisterRequest.username = authorizedPersonNameTIET.text.toString()
        return  true
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
