package com.jbpi.steamkotlin.api

import com.jbpi.steamkotlin.Achievement
import com.jbpi.steamkotlin.AchievementData
import com.jbpi.steamkotlin.ArrayAchievementsData
import com.jbpi.steamkotlin.communication.CurrentAlienSwarmAchievements
import com.jbpi.steamkotlin.communication.CurrentKillingFloor2Achievements
import io.reactivex.Observable
import javax.inject.Inject

class SteamWebApiClient @Inject constructor(var steamWebApiService: SteamWebApiService) {

    fun getAchievementsKillingFloor2(): Observable<CurrentKillingFloor2Achievements> {

        return this.steamWebApiService.getAchievementsKillingFloor2().map { arrayAchievementsData: ArrayAchievementsData -> convertResultKillingFloor2(arrayAchievementsData.achievements.toTypedArray()) }
    }

    fun getAchievementsAlienSwarm(): Observable<CurrentAlienSwarmAchievements> {
        return this.steamWebApiService.getAchievementsAlienSwarm().map { arrayAchievementsData: ArrayAchievementsData -> convertResultAlienSwarm(arrayAchievementsData.achievements.toTypedArray()) }
    }

    private fun convertResultKillingFloor2(listAchievementData: Array<AchievementData>): CurrentKillingFloor2Achievements {
        return CurrentKillingFloor2Achievements(convertToAchievementList(listAchievementData))
    }

    private fun convertResultAlienSwarm(listAchievementData: Array<AchievementData>): CurrentAlienSwarmAchievements {
        return CurrentAlienSwarmAchievements(convertToAchievementList(listAchievementData))
    }

    private fun convertToAchievementList(listAchievementData: Array<AchievementData>): Array<Achievement> {

        return listAchievementData.map { (apiName, achieved, _, name, description) -> Achievement(name, description, achieved == 1, apiName) }.toTypedArray()
    }
}