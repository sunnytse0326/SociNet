package com.socinet.viewmodel

import android.arch.lifecycle.*
import com.github.kittinunf.fuel.core.FuelError
import com.socinet.model.User
import com.socinet.repository.UserRepository

class UserViewModel(val lifecycle: Lifecycle, private val lifecycleOwner: LifecycleOwner): ViewModel() {
    val userList: MutableLiveData<List<User>> = MutableLiveData()
    val error: MutableLiveData<FuelError> = MutableLiveData()

    val userRepository: UserRepository = UserRepository()

    fun getUsers(): MutableLiveData<List<User>> {
        userRepository.fetchUser()?.subscribe{ result, _ ->
            val (data, err) = result
            if(err == null){
                userList.value = data
            } else{
                error.value = err
            }
        }
        return userList
    }

    fun getErrors(): MutableLiveData<FuelError>{
        return error
    }

    class VMFactory(private val lifecycle: Lifecycle, private val lifecycleOwner: LifecycleOwner) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T = UserViewModel(lifecycle, lifecycleOwner) as T
    }
}