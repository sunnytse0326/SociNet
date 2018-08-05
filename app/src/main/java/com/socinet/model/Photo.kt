package com.socinet.model

import com.github.kittinunf.fuel.android.core.Json
import com.github.kittinunf.fuel.core.Deserializable
import com.github.kittinunf.fuel.core.Response
import com.socinet.utils.asSequence
import org.json.JSONObject
import java.nio.charset.Charset

data class Photo(val albumId: Int = 0, val id: Int = 0, val title: String = "", val url: String = "", val thumbnailUrl: String = ""){
    companion object {
        fun init(json: JSONObject): Photo {
            return Photo(json.getInt("albumId"), json.getInt("id"), json.getString("title"), json.getString("url"), json.getString("thumbnailUrl"))
        }
    }

    class Deserializer : Deserializable<Photo> {
        override fun deserialize(response: Response): Photo {
            val json = Json(response.data.toString(Charset.defaultCharset()))
            return Photo.init(json.obj())
        }
    }

    class ListDeserializer : Deserializable<MutableList<Photo>> {
        override fun deserialize(response: Response): MutableList<Photo> {
            val json = Json(response.data.toString(Charset.defaultCharset()))
            return json.array().asSequence().map{ Photo.init(it as JSONObject) }.toMutableList()
        }
    }
}