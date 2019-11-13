package com.amaz.dev.android.jobsaround.ui.ownerJobs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amaz.dev.android.jobsaround.R
import com.amaz.dev.android.jobsaround.models.Job
import kotlinx.android.synthetic.main.owner_job_item.view.*

class OwnerJobsAdapter : ListAdapter<Job , OwnerJobsAdapter.JobViewHolder>(DiffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {

        return JobViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.owner_job_item,parent,false))
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {

        holder.bind(getItem(position))
    }


    object DiffCallback : DiffUtil.ItemCallback<Job>() {
        override fun areItemsTheSame(oldItem: Job, newItem: Job): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Job, newItem: Job): Boolean {
            return oldItem == newItem
        }
    }
    class JobViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(job: Job){
            itemView.jobNumberTV.text = "${(adapterPosition+1)} #"
            itemView.jobTitleTV.text = job.jobTitle
            itemView.jodDateTV.text = job.createdAt
            itemView.genderTV.text = job.gender.toString()

        }

    }
}