package com.socinet.repository

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.rx.rx_object
import com.github.kittinunf.result.Result
import com.socinet.model.User
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

open class BaseRepository{
    val apiSize: Int
    init {
        FuelManager.instance.basePath = "https://jsonplaceholder.typicode.com"
        apiSize = 50
    }
}