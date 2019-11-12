package com.amaz.dev.android.jobsaround.ui.auth.login


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController

import com.amaz.dev.android.jobsaround.R
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {


    val viewModel : LoginViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.error.observe(this , Observer {
            it?.let {
                Toast.makeText(context , it , Toast.LENGTH_SHORT).show()
            }
        })

        sendCodeBtn.setOnClickListener {

            if (phoneNumberTi.text.isNullOrEmpty()) {
                Toast.makeText(context , "يرجى ادخال البيانات" , Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            viewModel.login(phoneNumberTi.text.toString()).observe(this , Observer {

                if (it != null){
                    var bundel = Bundle()
                    bundel.putString("code" ,it.code)
                    findNavController().navigate(R.id.action_loginFragment_to_phoneVerification ,bundel)
                }
            })
        }
    }

}
