package com.jbpi.steamkotlin

import dagger.Component
import javax.inject.Singleton

@Component(modules = arrayOf(NetworkModule::class))
@Singleton
interface HomeActivityComponent {

    fun inject(homeActivity: HomeActivity)
}