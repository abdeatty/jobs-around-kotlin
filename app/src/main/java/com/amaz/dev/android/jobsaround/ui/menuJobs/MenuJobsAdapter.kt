package com.amaz.dev.android.jobsaround.ui.menuJobs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.amaz.dev.android.jobsaround.R
import com.amaz.dev.android.jobsaround.models.JobDetails
import androidx.recyclerview.widget.ListAdapter
import kotlinx.android.synthetic.main.job_item.view.*

class MenuJobsAdapter : androidx.recyclerview.widget.ListAdapter<JobDetails, MenuJobsAdapter.JobViewHolder>(DiffCallback) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuJobsAdapter.JobViewHolder {

        return MenuJobsAdapter.JobViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.job_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MenuJobsAdapter.JobViewHolder, position: Int) {

        holder.bind(getItem(position))
    }


    object DiffCallback : DiffUtil.ItemCallback<JobDetails>() {
        override fun areItemsTheSame(oldItem: JobDetails, newItem: JobDetails): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: JobDetails, newItem: JobDetails): Boolean {
            return oldItem == newItem
        }

    }

    class JobViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bind(job: JobDetails){
            itemView.foundationNameTV.text = job.owner?.username
            itemView.jobTitleTV.text = job.jobTitle

        }
    }
}