package com.mytaxi.demo

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.result.Result
import com.google.gson.Gson
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DemoApplication

fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
    val apiClient = ApiClient()
    apiClient.getWeatherInfo("bo")
    apiClient.getWeatherByGps(Pair(50.068, -5.316)).map { println(it) }

}


class ApiClient {

    fun getWeatherInfo(query: String) {
        Fuel.get("https://www.metaweather.com/api/location/search/?query="
                + query).responseObject(Response.Deserializer()) { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    println(ex)
                }
                is Result.Success -> {
                    result.get().map { println(it.title) }

                }
            }
        }
    }

    fun getWeatherByGps(location: Pair<Double, Double>): Array<Response> {
        val (request, response, result) = Fuel.get("https://www.metaweather.com/api/location/search/?lattlong="
                + location.first + "," + location.second).responseString()
        val res = Gson().fromJson(result.component1(), Array<Response>::class.java)
        return res
    }
}
