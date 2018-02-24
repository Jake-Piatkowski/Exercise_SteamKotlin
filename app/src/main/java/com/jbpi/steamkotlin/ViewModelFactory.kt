package com.jbpi.steamkotlin

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject

class ViewModelFactory @Inject constructor(var achievementRepo: AchievementRepo) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>?): T {
        return HomeViewModel(achievementRepo) as T
    }
}