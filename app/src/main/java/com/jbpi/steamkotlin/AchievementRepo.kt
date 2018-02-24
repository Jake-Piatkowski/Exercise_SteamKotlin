package com.jbpi.steamkotlin

import com.jbpi.steamkotlin.api.SteamWebApiClient
import com.jbpi.steamkotlin.communication.CurrentAlienSwarmAchievements
import com.jbpi.steamkotlin.communication.CurrentKillingFloor2Achievements
import io.reactivex.Observable
import javax.inject.Inject

class AchievementRepo @Inject constructor(var steamWebApiClient: SteamWebApiClient) {

    fun getAchievementsKillingFloor2(): Observable<CurrentKillingFloor2Achievements> {
        return steamWebApiClient.getAchievementsKillingFloor2()
    }

    fun getAchievementsAlienSwarm(): Observable<CurrentAlienSwarmAchievements> {
        return steamWebApiClient.getAchievementsAlienSwarm()
    }
}