package com.amaz.dev.android.jobsaround.di

import com.amaz.dev.android.jobsaround.repositories.IJobsRepo
import com.amaz.dev.android.jobsaround.repositories.JobsRepo
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import sa.amaz.jaz.teacher.network.Network
import sa.amaz.jaz.teacher.network.RemoteDataSource


private val network =  module {
    factory { Network.apiService }
}

private val remoteModule = module { factory { RemoteDataSource(get()) } }

private val repositoryModule = module { single<IJobsRepo> {JobsRepo(get())} }

private val viewModelModule = module {


}

fun getModules() : Array<Module>{

    return  arrayOf(
        network,
        remoteModule,
        repositoryModule,
        viewModelModule
    )
}