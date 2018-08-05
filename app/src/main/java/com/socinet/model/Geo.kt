package com.socinet.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Geo(val lat: Double = 0.0, val lng: Double = 0.0): Parcelable