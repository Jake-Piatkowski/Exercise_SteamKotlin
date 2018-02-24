package com.jbpi.steamkotlin

import android.os.Parcel
import android.os.Parcelable

data class Achievement(
        val name: String?,
        val description: String?,
        val unlocked: Boolean,
        val steamId: String
) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readByte() != 0.toByte(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeByte(if (unlocked) 1 else 0)
        parcel.writeString(steamId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Achievement> {
        override fun createFromParcel(parcel: Parcel): Achievement {
            return Achievement(parcel)
        }

        override fun newArray(size: Int): Array<Achievement?> {
            return arrayOfNulls(size)
        }
    }
}