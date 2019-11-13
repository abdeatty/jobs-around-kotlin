package com.amaz.dev.android.jobsaround.ui.ownerJobs


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer

import com.amaz.dev.android.jobsaround.R
import kotlinx.android.synthetic.main.fragment_owner_jobs.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class OwnerJobsFragment : Fragment() {

    private val viewModel : OwnerJobsViewModel by viewModel()
    private val adapter by lazy { OwnerJobsAdapter() }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_owner_jobs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        jobsRV.setHasFixedSize(true)
        jobsRV.adapter = adapter

        viewModel.error.observe(this , Observer {
            it?.let {
                Toast.makeText(context ,it ,Toast.LENGTH_LONG).show()
            }
        })

        viewModel.getOwnerJobs().observe(this , Observer {

            it?.let {
                adapter.submitList(it)
            }
        })
    }



}
