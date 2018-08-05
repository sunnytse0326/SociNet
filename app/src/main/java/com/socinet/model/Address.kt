package com.socinet.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Address(val street: String = "", val suite: String = "", val city: String = "", val zipcode: String = "", val geo: Geo = Geo()) : Parcelable