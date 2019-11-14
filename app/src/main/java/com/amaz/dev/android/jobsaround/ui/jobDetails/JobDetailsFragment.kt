package com.amaz.dev.android.jobsaround.ui.jobDetails


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer

import com.amaz.dev.android.jobsaround.R
import com.amaz.dev.android.jobsaround.helpers.Utilities
import com.amaz.dev.android.jobsaround.models.JobDetails
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_job_details.*
import kotlinx.android.synthetic.main.tool_bar.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class JobDetailsFragment : Fragment() {

    private val viewModel : JobDetailsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_job_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolBarIcon2.setImageDrawable(ContextCompat.getDrawable(context!!,R.drawable.ic_arrow_point_to_right))
        toolBarIcon2.setOnClickListener { activity?.onBackPressed() }
        appBarTitle.text = "رقم الاعلان"



        viewModel.error.observe(this , Observer {

            it?.let {
                Toast.makeText(context,it,Toast.LENGTH_LONG).show()
            }
        })

         var bundle = arguments
        if (bundle != null) {
            var jobId = bundle.getInt("jobId", 0)

            viewModel.getJobDetails(jobId).observe(this, Observer {

                it?.let {
                    bindData(it)
                    mainView.visibility = View.VISIBLE
                }
            })
        }
    }

    private fun bindData(jobDetails: JobDetails){

        Glide.with(view!!).load(jobDetails.owner?.icon).placeholder(R.drawable.ic_logo).into(foundationImgV)
        foundationNameTV.text = jobDetails.owner?.username
        jobTitleTV.text = jobDetails.jobTitle
        genderTV.text = Utilities.getGenderName(jobDetails.gender!!)
        nationalityTV.text = jobDetails.national
        spicalizationTV.text = jobDetails.qualification
        yearsOfExperienceTV.text = jobDetails.experiance
        jobDescTV.text = jobDetails.description
    }

}
