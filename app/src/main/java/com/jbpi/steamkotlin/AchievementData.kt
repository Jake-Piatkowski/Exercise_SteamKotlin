package com.jbpi.steamkotlin

data class ArrayAchievementsData(val achievements: List<AchievementData>
)

data class AchievementData(val apiname: String,
                           val achieved: Int,
                           val unlocktime: Long,
                           val name: String?,
                           val description: String?)