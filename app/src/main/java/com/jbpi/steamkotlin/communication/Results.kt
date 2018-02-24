package com.jbpi.steamkotlin.communication

import com.jbpi.steamkotlin.Achievement

interface Result

data class CurrentKillingFloor2Achievements(val listAchievements: Array<Achievement>) : Result
data class CurrentAlienSwarmAchievements(val listAchievements: Array<Achievement>) : Result