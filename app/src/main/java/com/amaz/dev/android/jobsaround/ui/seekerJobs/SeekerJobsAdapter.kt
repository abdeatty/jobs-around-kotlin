package com.amaz.dev.android.jobsaround.ui.seekerJobs

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.amaz.dev.android.jobsaround.R
import com.amaz.dev.android.jobsaround.helpers.ItemClickListener


class SeekerJobsAdapter(private val context: Context, private var itemClickListener : ItemClickListener<String>) : RecyclerView.Adapter<SeekerJobsAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.job_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.setOnClickListener { itemClickListener.onItemClicked("") }
    }

    override fun getItemCount(): Int {
        return 10
    }

     class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
