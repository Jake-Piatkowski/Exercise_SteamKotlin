package com.jbpi.steamkotlin.communication

import com.jbpi.steamkotlin.Achievement

sealed class UiModel

data class KillingFloor2UiModel(val listAchievements: Array<Achievement>) : UiModel()
data class AlienSwarmUiModel(val listAchievements: Array<Achievement>) : UiModel()