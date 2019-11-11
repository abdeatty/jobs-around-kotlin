package com.amaz.dev.android.jobsaround.ui.chat


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat

import com.amaz.dev.android.jobsaround.R
import com.amaz.dev.android.jobsaround.ui.home.HomeFragment
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.tool_bar.*

/**
 * A simple [Fragment] subclass.
 */
class ChatFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        toolBarIcon2.setImageDrawable(ContextCompat.getDrawable(context!!,R.drawable.ic_arrow_point_to_right))
        toolBarIcon2.setOnClickListener { activity?.onBackPressed() }
        appBarTitle.text = "اسم الشخص"

    }

}
