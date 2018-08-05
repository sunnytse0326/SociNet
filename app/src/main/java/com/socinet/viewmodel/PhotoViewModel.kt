package com.socinet.viewmodel

import android.arch.lifecycle.*
import com.github.kittinunf.fuel.core.FuelError
import com.socinet.model.Photo
import com.socinet.repository.PhotoRepository

class PhotoViewModel(val lifecycle: Lifecycle, private val lifecycleOwner: LifecycleOwner): ViewModel() {
    val photoList: MutableLiveData<MutableList<Photo>> = MutableLiveData()
    val error: MutableLiveData<FuelError> = MutableLiveData()

    val photoRepository: PhotoRepository = PhotoRepository()

    fun getPhotos(start: Int, albumId: Int): MutableLiveData<MutableList<Photo>> {
        photoRepository.fetchPhotos(start * photoRepository.apiSize, photoRepository.apiSize, albumId)?.subscribe{ result, _ ->
            val (data, err) = result
            if(err == null){
                photoList.value = data
            } else{
                error.value = err
            }
        }
        return photoList
    }

    fun getErrors(): MutableLiveData<FuelError>{
        return error
    }

    class VMFactory(private val lifecycle: Lifecycle, private val lifecycleOwner: LifecycleOwner) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T = PhotoViewModel(lifecycle, lifecycleOwner) as T
    }
}