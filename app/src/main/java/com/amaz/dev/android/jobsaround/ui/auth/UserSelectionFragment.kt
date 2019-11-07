package com.amaz.dev.android.jobsaround.ui.auth


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import com.amaz.dev.android.jobsaround.R
import kotlinx.android.synthetic.main.fragment_user_selection.*

/**
 * A simple [Fragment] subclass.
 */
class UserSelectionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_selection, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        ownerTV.setOnClickListener {
            findNavController().navigate(R.id.action_userSelectionFragment_to_ownerRegisterFragment)
        }

        seekerTV.setOnClickListener {
            findNavController().navigate(R.id.action_userSelectionFragment_to_seekerRegisterFragment)
        }

        loginTV.setOnClickListener{
            findNavController().navigate(R.id.action_userSelectionFragment_to_loginFragment)
        }

    }

}
