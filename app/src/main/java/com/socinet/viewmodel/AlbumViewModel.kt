package com.socinet.viewmodel

import android.arch.lifecycle.*
import com.github.kittinunf.fuel.core.FuelError
import com.socinet.model.Album
import com.socinet.repository.AlbumRepository

class AlbumViewModel(val lifecycle: Lifecycle, private val lifecycleOwner: LifecycleOwner): ViewModel() {
    val albumList: MutableLiveData<MutableList<Album>> = MutableLiveData()
    val error: MutableLiveData<FuelError> = MutableLiveData()

    val albumRepository: AlbumRepository = AlbumRepository()

    fun getPosts(start: Int, userId: Int): MutableLiveData<MutableList<Album>> {
        albumRepository.fetchAlbum(start * albumRepository.apiSize, albumRepository.apiSize, userId)?.subscribe{ result, _ ->
            val (data, err) = result
            if(err == null){
                albumList.value = data
            } else{
                error.value = err
            }
        }
        return albumList
    }

    fun getErrors(): MutableLiveData<FuelError>{
        return error
    }

    class VMFactory(private val lifecycle: Lifecycle, private val lifecycleOwner: LifecycleOwner) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T = AlbumViewModel(lifecycle, lifecycleOwner) as T
    }
}