package com.mytaxi.demo.model

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

/*Generic data type for the weather api response */
data class Response constructor(var title: String, var locationType: String, var woeid: Int, var lattLong: String) {

    /*Internal deserializer for the class, this allows to keep the API Call cleaner
         and provides the capability to deserialize at different layers */
    class Deserializer : ResponseDeserializable<Array<Response>> {
        override fun deserialize(content: String): Array<Response>? =
                Gson().fromJson(content, Array<Response>::class.java)
    }
}