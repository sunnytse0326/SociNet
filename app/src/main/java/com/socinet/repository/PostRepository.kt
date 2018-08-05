package com.socinet.repository

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.rx.rx_object
import com.github.kittinunf.result.Result
import com.socinet.model.Post
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PostRepository: BaseRepository() {
    fun fetchPost(start: Int, limited: Int, userId: Int): Single<Result<MutableList<Post>, FuelError>>? = Fuel.get("/posts?_start=$start&_limit=$limited&userId=$userId").rx_object(Post.ListDeserializer()).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
}