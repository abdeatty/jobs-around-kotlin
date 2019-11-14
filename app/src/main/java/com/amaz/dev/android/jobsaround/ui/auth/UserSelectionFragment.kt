package com.amaz.dev.android.jobsaround.ui.auth


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import com.amaz.dev.android.jobsaround.R
import com.amaz.dev.android.jobsaround.helpers.Constants
import com.amaz.dev.android.jobsaround.helpers.Constants.OWNER
import com.amaz.dev.android.jobsaround.helpers.Constants.SEEKER
import com.android.airbag.helpers.SharedPreferencesManager
import kotlinx.android.synthetic.main.fragment_user_selection.*
import kotlinx.android.synthetic.main.tool_bar.*

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

        appBarTitle.text = getString(R.string.new_register)

        ownerTV.setOnClickListener {
            SharedPreferencesManager.setIntValue(context!!,Constants.USER_TYPE,OWNER)
            findNavController().navigate(R.id.action_userSelectionFragment_to_rulesFragment)
        }

        seekerTV.setOnClickListener {
            SharedPreferencesManager.setIntValue(context!!,Constants.USER_TYPE,SEEKER)
            findNavController().navigate(R.id.action_userSelectionFragment_to_rulesFragment)
        }

        loginTV.setOnClickListener{
            findNavController().navigate(R.id.action_userSelectionFragment_to_loginFragment)
        }

    }

}
