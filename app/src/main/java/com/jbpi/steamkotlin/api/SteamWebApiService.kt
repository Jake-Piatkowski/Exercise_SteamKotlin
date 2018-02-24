package com.jbpi.steamkotlin.api

import com.jbpi.steamkotlin.AchievementData
import com.jbpi.steamkotlin.ArrayAchievementsData
import io.reactivex.Observable
import retrofit2.http.GET

interface SteamWebApiService {

    // http://api.steampowered.com/ISteamUserStats/
    // http://api.steampowered.com/ISteamUserStats/GetPlayerAchievements/v0001/?appid=440&key=XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX&steamid=76561197972495328

    @GET("GetPlayerAchievements/v0001/?appid=232090&key=<key>&steamid=76561198048943926")
    fun getAchievementsKillingFloor2(): Observable<ArrayAchievementsData>

    @GET("GetPlayerAchievements/v0001/?appid=630&key=<key>&steamid=76561198048943926")
    fun getAchievementsAlienSwarm(): Observable<ArrayAchievementsData>
}