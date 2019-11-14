package com.amaz.dev.android.jobsaround.ui.home


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController

import com.amaz.dev.android.jobsaround.R
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navHostFragment = view.findViewById<View>(R.id.home_nav_host_fragment)
        val navController = Navigation.findNavController(navHostFragment)
        bottomNavView.setupWithNavController(navController)




        navController.addOnDestinationChangedListener { _, navDestination: NavDestination, _ ->
            if (navDestination.id == R.id.mapJobsFragment || navDestination.id == R.id.profileFragment
                || navDestination.id == R.id.chatUserListFragment || navDestination.id == R.id.menuJobsFragment) {
                bottomNavView.visibility = View.VISIBLE
            } else {
                bottomNavView.visibility = View.GONE
            }
        }


    }
}
