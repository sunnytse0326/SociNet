package com.socinet.repository

import com.github.kittinunf.fuel.core.FuelManager

open class BaseRepository{
    val apiSize: Int
    init {
        FuelManager.instance.basePath = "https://jsonplaceholder.typicode.com"
        apiSize = 50
    }
}