package com.socinet.viewmodel

import android.arch.lifecycle.*
import com.github.kittinunf.fuel.core.FuelError
import com.socinet.model.Todo
import com.socinet.repository.TodoRepository

class CommentViewModel(val lifecycle: Lifecycle, private val lifecycleOwner: LifecycleOwner): ViewModel() {
    val todoList: MutableLiveData<MutableList<Todo>> = MutableLiveData()
    val error: MutableLiveData<FuelError> = MutableLiveData()

    val todoRepository: TodoRepository = TodoRepository()

    fun getTodos(start: Int, userId: Int): MutableLiveData<MutableList<Todo>> {
        todoRepository.fetchTodos(start * todoRepository.apiSize, todoRepository.apiSize, userId)?.subscribe{ result, _ ->
            val (data, err) = result
            if(err == null){
                todoList.value = data
            } else{
                error.value = err
            }
        }
        return todoList
    }

    fun addPosts(){

    }

    fun getErrors(): MutableLiveData<FuelError>{
        return error
    }

    class VMFactory(private val lifecycle: Lifecycle, private val lifecycleOwner: LifecycleOwner) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T = CommentViewModel(lifecycle, lifecycleOwner) as T
    }
}