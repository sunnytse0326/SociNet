package com.socinet.model

import android.os.Parcelable
import com.github.kittinunf.fuel.android.core.Json
import com.github.kittinunf.fuel.core.Deserializable
import com.github.kittinunf.fuel.core.Response
import com.socinet.utils.asSequence
import org.json.JSONObject
import kotlinx.android.parcel.Parcelize
import java.nio.charset.Charset

@Parcelize
data class User(val id: Int = 0, val name: String, val username: String = "", val email: String = "", val phone: String = "", val website: String = "", val address: Address = Address(), val company: Company = Company()): Parcelable {
    companion object {
        fun init(json: JSONObject): User {
            val addressJson = json.getJSONObject("address")
            val companyJson = json.getJSONObject("company")
            val geoJson = addressJson.getJSONObject("geo")
            val address = Address(addressJson.getString("street"), addressJson.getString("suite"), addressJson.getString("city"), addressJson.getString("zipcode"), Geo(geoJson.getDouble("lat"), geoJson.getDouble("lng")))
            val company = Company(companyJson.getString("name"), companyJson.getString("catchPhrase"), companyJson.getString("bs"))
            return User(json.getInt("id"), json.getString("name"), json.getString("username"), json.getString("email"), json.getString("phone"), json.getString("website"), address, company)
        }
    }

    class Deserializer : Deserializable<User> {
        override fun deserialize(response: Response): User {
            val json = Json(response.data.toString(Charset.defaultCharset()))
            return User.init(json.obj())
        }
    }

    class ListDeserializer : Deserializable<List<User>> {
        override fun deserialize(response: Response): List<User> {
            val json = Json(response.data.toString(Charset.defaultCharset()))
            return json.array().asSequence().map{ User.init(it as JSONObject) }.toList()
        }
    }
}