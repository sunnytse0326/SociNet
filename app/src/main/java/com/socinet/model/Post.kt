package com.socinet.model

import com.github.kittinunf.fuel.android.core.Json
import com.github.kittinunf.fuel.core.Deserializable
import com.github.kittinunf.fuel.core.Response
import com.socinet.utils.asSequence
import org.json.JSONObject
import java.nio.charset.Charset

data class Post(val userId: Int = 0, val id: Int = 0, val title: String = "", val body: String = ""){
    companion object {
        fun init(json: JSONObject): Post {
            return Post(json.getInt("userId"), json.getInt("id"), json.getString("title"), json.getString("body"))
        }
    }

    class Deserializer : Deserializable<Post> {
        override fun deserialize(response: Response): Post {
            val json = Json(response.data.toString(Charset.defaultCharset()))
            return Post.init(json.obj())
        }
    }

    class ListDeserializer : Deserializable<MutableList<Post>> {
        override fun deserialize(response: Response): MutableList<Post> {
            val json = Json(response.data.toString(Charset.defaultCharset()))
            return json.array().asSequence().map{ Post.init(it as JSONObject) }.toMutableList()
        }
    }
}