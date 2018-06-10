package com.mytaxi.demo

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.result.Result
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DemoApplication

fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
    val apiClient = ApiClient()
    apiClient.getWeatherInfo("bo").map { print(it.title) }
    apiClient.getWeatherByGps(Pair(50.068, -5.316)).map { println(it) }

}


class ApiClient {

    fun getWeatherInfo(query: String): Array<Response> {
        var data = arrayOf<Response>()
        Fuel.get("https://www.metaweather.com/api/location/search/?query="
                + query).responseObject(Response.Deserializer()) { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    println(ex)
                }
                is Result.Success -> {
                    data = result.get()
                }
            }
        }
        return data
    }

    fun getWeatherByGps(location: Pair<Double, Double>): Array<Response> {
        var data = arrayOf<Response>()
        Fuel.get("https://www.metaweather.com/api/location/search/?lattlong="
                + location.first + "," + location.second).responseObject(Response.Deserializer()) { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    println(ex)
                }
                is Result.Success -> {
                    data = result.get()
                }
            }
        }
        return data
    }
}