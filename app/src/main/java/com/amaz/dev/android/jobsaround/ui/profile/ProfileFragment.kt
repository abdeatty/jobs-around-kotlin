package com.amaz.dev.android.jobsaround.ui.profile


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import com.amaz.dev.android.jobsaround.R
import com.amaz.dev.android.jobsaround.helpers.Constants
import com.android.airbag.helpers.SharedPreferencesManager
import kotlinx.android.synthetic.main.fragment_profile.*

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var userType = SharedPreferencesManager.getIntValue(context!!,Constants.USER_TYPE)
        if (userType == 1) createJobButton.visibility = View.GONE


        createJobButton.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_createJobFragment)
        }

        myJobsButton.setOnClickListener {
            if (userType == 1)
            findNavController().navigate(R.id.action_profileFragment_to_seekerJobFragment)
            else
                findNavController().navigate(R.id.action_profileFragment_to_ownerJobsFragment)
        }

        myInfoBtn.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_seekerInfoFragment)
        }

         feesBtn.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_feesFragment)
        }


    }

}
