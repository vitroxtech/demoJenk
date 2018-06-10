package com.mytaxi.demo

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson


data class Response constructor(var title: String, var locationType: String, var woeid: Int, var lattLong: String) {

    class Deserializer : ResponseDeserializable<Array<Response>> {
        override fun deserialize(content: String): Array<Response>? = Gson().fromJson(content, Array<Response>::class.java)
    }
}