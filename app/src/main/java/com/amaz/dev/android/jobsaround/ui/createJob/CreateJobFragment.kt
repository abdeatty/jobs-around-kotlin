package com.amaz.dev.android.jobsaround.ui.createJob


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController

import com.amaz.dev.android.jobsaround.R
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.tool_bar.*

/**
 * A simple [Fragment] subclass.
 */
class CreateJobFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_job, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolBarIcon2.setImageDrawable(ContextCompat.getDrawable(context!!,R.drawable.ic_arrow_point_to_right))
        toolBarIcon2.setOnClickListener { activity?.onBackPressed() }
        appBarTitle.text = getString(R.string.add_job)
    }


}
