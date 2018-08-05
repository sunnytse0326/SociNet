package com.socinet.model

import com.github.kittinunf.fuel.android.core.Json
import com.github.kittinunf.fuel.core.Deserializable
import com.github.kittinunf.fuel.core.Response
import com.socinet.utils.asSequence
import org.json.JSONObject
import java.nio.charset.Charset

data class Todo(val userId: Int = 0, val id: Int = 0, val title: String = "", val completed: String = ""){
    companion object {
        fun init(json: JSONObject): Todo {
            return Todo(json.getInt("userId"), json.getInt("id"), json.getString("title"), json.getString("completed"))
        }
    }

    class Deserializer : Deserializable<Todo> {
        override fun deserialize(response: Response): Todo {
            val json = Json(response.data.toString(Charset.defaultCharset()))
            return Todo.init(json.obj())
        }
    }

    class ListDeserializer : Deserializable<MutableList<Todo>> {
        override fun deserialize(response: Response): MutableList<Todo> {
            val json = Json(response.data.toString(Charset.defaultCharset()))
            return json.array().asSequence().map{ Todo.init(it as JSONObject) }.toMutableList()
        }
    }
}