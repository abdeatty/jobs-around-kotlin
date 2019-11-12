package com.amaz.dev.android.jobsaround.ui.seekerJobs


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import com.amaz.dev.android.jobsaround.R
import com.amaz.dev.android.jobsaround.helpers.ItemClickListener
import kotlinx.android.synthetic.main.fragment_seeker_jobs.*

/**
 * A simple [Fragment] subclass.
 */
class SeekerJobsFragment : Fragment() ,ItemClickListener<String>{

    override fun onItemClicked(item: String) {
        findNavController().navigate(R.id.action_myJobsFragment_to_jobDetailsFragment)
    }

    private val adapter by lazy { SeekerJobsAdapter(context!!,this) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_seeker_jobs, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myJobsRv.adapter = adapter



    }
}
