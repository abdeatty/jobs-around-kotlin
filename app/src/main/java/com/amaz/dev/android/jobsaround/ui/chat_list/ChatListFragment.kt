package com.amaz.dev.android.jobsaround.ui.chat_list


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.amaz.dev.android.jobsaround.R
import com.amaz.dev.android.jobsaround.helpers.ItemClickListener
import com.amaz.dev.android.jobsaround.ui.home.HomeFragment
import kotlinx.android.synthetic.main.fragment_chat_list.*

/**
 * A simple [Fragment] subclass.
 */
class ChatListFragment : Fragment() , ItemClickListener<String> {


    override fun onItemClicked(item: String) {
        findNavController().navigate(R.id.action_chatListFragment_to_cahtFragment)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat_list, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initChatRv()


    }

    private fun initChatRv() {
         var chatFreindsAdapter = ChatFreindsAdapter(context,this)
        chatRv.adapter = chatFreindsAdapter
        chatRv.setHasFixedSize(true)
        chatRv.layoutManager = LinearLayoutManager(context)
    }
}
