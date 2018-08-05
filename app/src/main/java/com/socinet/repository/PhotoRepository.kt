package com.socinet.repository

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.rx.rx_object
import com.github.kittinunf.result.Result
import com.socinet.model.Photo
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PhotoRepository: BaseRepository() {
    fun fetchPhotos(start: Int, limited: Int, albumId: Int): Single<Result<MutableList<Photo>, FuelError>>? = Fuel.get("/photos?_start=$start&_limit=$limited&albumId=$albumId").rx_object(Photo.ListDeserializer()).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
}