package com.socinet.model

import com.github.kittinunf.fuel.android.core.Json
import com.github.kittinunf.fuel.core.Deserializable
import com.github.kittinunf.fuel.core.Response
import com.socinet.utils.asSequence
import org.json.JSONObject
import java.nio.charset.Charset

data class Album(val userId: Int = 0, val id: Int = 0, val title: String = ""){
    companion object {
        fun init(json: JSONObject): Album {
            return Album(json.getInt("userId"), json.getInt("id"), json.getString("title"))
        }
    }

    class Deserializer : Deserializable<Album> {
        override fun deserialize(response: Response): Album {
            val json = Json(response.data.toString(Charset.defaultCharset()))
            return Album.init(json.obj())
        }
    }

    class ListDeserializer : Deserializable<MutableList<Album>> {
        override fun deserialize(response: Response): MutableList<Album> {
            val json = Json(response.data.toString(Charset.defaultCharset()))
            return json.array().asSequence().map{ Album.init(it as JSONObject) }.toMutableList()
        }
    }
}