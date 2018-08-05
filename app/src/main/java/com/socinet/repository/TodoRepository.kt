package com.socinet.repository

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.rx.rx_object
import com.github.kittinunf.result.Result
import com.socinet.model.Todo
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TodoRepository: BaseRepository() {
    fun fetchTodos(start: Int, limited: Int, userId: Int): Single<Result<MutableList<Todo>, FuelError>>? = Fuel.get("/todos?_start=$start&_limit=$limited&userId=$userId").rx_object(Todo.ListDeserializer()).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
}