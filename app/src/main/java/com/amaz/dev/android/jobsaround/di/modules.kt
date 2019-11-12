package com.amaz.dev.android.jobsaround.di

import com.amaz.dev.android.jobsaround.repositories.IJobsRepo
import com.amaz.dev.android.jobsaround.repositories.JobsRepo
import com.amaz.dev.android.jobsaround.ui.auth.register.owner.OwnerRegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import com.amaz.dev.android.jobsaround.network.Network
import com.amaz.dev.android.jobsaround.network.RemoteDataSource
import com.amaz.dev.android.jobsaround.ui.auth.login.LoginViewModel
import com.amaz.dev.android.jobsaround.ui.auth.phoneverification.PhoneVerificationViewModel
import com.amaz.dev.android.jobsaround.ui.map.LocationViewModel
import com.amaz.dev.android.jobsaround.ui.auth.register.seeker.SeekerRegisterViewModel
import com.amaz.dev.android.jobsaround.ui.createJob.CreateJobViewModel
import com.amaz.dev.android.jobsaround.ui.ownerJobs.OwnerJobsViewModel


private val network =  module {
    factory { Network.apiService }
}

private val remoteModule = module { factory { RemoteDataSource(get()) } }

private val repositoryModule = module { single<IJobsRepo> {JobsRepo(get() , get())} }

private val viewModelModule = module {


    viewModel { OwnerRegisterViewModel(get()) }
    viewModel { SeekerRegisterViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { PhoneVerificationViewModel(get()) }
    viewModel { LocationViewModel() }
    viewModel { CreateJobViewModel(get()) }
    viewModel { OwnerJobsViewModel(get()) }
}

fun getModules() : Array<Module>{

    return  arrayOf(
        network,
        remoteModule,
        repositoryModule,
        viewModelModule
    )
}