package com.amaz.dev.android.jobsaround.ui.auth.phoneverification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.amaz.dev.android.jobsaround.R
import com.blankj.utilcode.util.KeyboardUtils
import kotlinx.android.synthetic.main.fragment_phone_verification.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class PhoneVerificationFragment : Fragment() {

    val viewModel : PhoneVerificationViewModel by viewModel()
    lateinit var code : String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(com.amaz.dev.android.jobsaround.R.layout.fragment_phone_verification, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = this.arguments
        if (bundle != null) {
            code = bundle.getString("code", "")
            firstNumTi.setText(code[0].toString())
            secondNumTi.setText(code[1].toString())
            thirdNumTi.setText(code[2].toString())
            forthNumTi.setText(code[3].toString())
        }

        viewModel.error.observe(this , Observer {
            Toast.makeText(context , it , Toast.LENGTH_SHORT).show()
        })
        loginBtn.setOnClickListener {

            viewModel.virifyCode(code).observe(this , Observer {

                it?.let {
                    findNavController().navigate(R.id.action_phoneVerification_to_homeFragment)
                    KeyboardUtils.hideSoftInput(getView())
                }
            })

        }

//        ownerTV.setOnClickListener {
//            findNavController().navigate(R.id.action_userSelectionFragment_to_ownerRegisterFragment)
//        }
//
//        seekerTV.setOnClickListener {
//            findNavController().navigate(R.id.action_userSelectionFragment_to_seekerRegisterFragment)
//        }
//
//        loginTV.setOnClickListener{
//            findNavController().navigate(R.id.action_userSelectionFragment_to_loginFragment)
//        }

    }

}