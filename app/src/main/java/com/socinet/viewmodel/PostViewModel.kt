package com.socinet.viewmodel

import android.arch.lifecycle.*
import com.github.kittinunf.fuel.core.FuelError
import com.socinet.model.Post
import com.socinet.repository.PostRepository

class PostViewModel(val lifecycle: Lifecycle, private val lifecycleOwner: LifecycleOwner): ViewModel() {
    val postList: MutableLiveData<MutableList<Post>> = MutableLiveData()
    val error: MutableLiveData<FuelError> = MutableLiveData()

    val postRepository: PostRepository = PostRepository()

    fun getPosts(start: Int, userId: Int): MutableLiveData<MutableList<Post>> {
        postRepository.fetchPost(start * postRepository.apiSize, postRepository.apiSize, userId)?.subscribe{ result, _ ->
            val (data, err) = result
            if(err == null){
                postList.value = data
            } else{
                error.value = err
            }
        }
        return postList
    }

    fun addPosts(){

    }

    fun getErrors(): MutableLiveData<FuelError>{
        return error
    }

    class VMFactory(private val lifecycle: Lifecycle, private val lifecycleOwner: LifecycleOwner) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T = PostViewModel(lifecycle, lifecycleOwner) as T
    }
}