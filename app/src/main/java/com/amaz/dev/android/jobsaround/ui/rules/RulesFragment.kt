package com.amaz.dev.android.jobsaround.ui.rules


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import com.amaz.dev.android.jobsaround.R
import kotlinx.android.synthetic.main.fragment_rules.*

/**
 * A simple [Fragment] subclass.
 */
class RulesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rules, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        agreeButton.setOnClickListener {
            findNavController().navigate(R.id.action_rulesFragment_to_feesFragment)
        }
    }
}
