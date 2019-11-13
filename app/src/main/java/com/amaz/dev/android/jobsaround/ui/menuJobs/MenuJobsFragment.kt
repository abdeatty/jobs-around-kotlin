package com.amaz.dev.android.jobsaround.ui.menuJobs


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer

import com.amaz.dev.android.jobsaround.R
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.fragment_menu_jobs.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class MenuJobsFragment : Fragment() {


    private val viewModel : MenuJobsViewModel by viewModel()
    private val adapter : MenuJobsAdapter by lazy { MenuJobsAdapter() }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu_jobs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.error.observe(this , Observer {

            it?.let {
                Toast.makeText(context,it,Toast.LENGTH_LONG).show()
            }
        })

        val latLng = LatLng(31.210361 , 29.936940)
        viewModel.getNearestJobsForSeeker(latLng).observe(this , Observer {

            it?.let {

                jobsRV.setHasFixedSize(true)
                jobsRV.adapter = adapter
                adapter.submitList(it)
            }
        })
    }


}
