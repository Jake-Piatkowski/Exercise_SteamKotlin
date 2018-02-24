package com.jbpi.steamkotlin.api

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.jbpi.steamkotlin.ArrayAchievementsData
import java.lang.reflect.Type

class MyJsonDeserializer : JsonDeserializer<ArrayAchievementsData> {

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): ArrayAchievementsData {

        val playerStatsJson: JsonElement? = json?.asJsonObject?.getAsJsonObject("playerstats")

        return Gson().fromJson(playerStatsJson, ArrayAchievementsData::class.java)
    }
}