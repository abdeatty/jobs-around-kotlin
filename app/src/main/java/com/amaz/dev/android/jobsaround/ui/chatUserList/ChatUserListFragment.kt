package com.amaz.dev.android.jobsaround.ui.chatUserList


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.amaz.dev.android.jobsaround.R

/**
 * A simple [Fragment] subclass.
 */
class ChatUserListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat_user_list, container, false)
    }


}
