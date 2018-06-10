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
    apiClient.getWeatherInfo("bo")
    apiClient.getWeatherByGps(Pair(50.068, -5.316))
}


class ApiClient {

    fun getWeatherInfo(query: String) {

        Fuel.get("https://www.metaweather.com/api/location/search/?query="
                + query).responseObject(Response.Deserializer()) { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                }
                is Result.Success -> {
                    val data = result.get()
                    data.map { println(it.title) }
                }
            }
        }
    }

    fun getWeatherByGps(location: Pair<Double, Double>) {

        Fuel.get("https://www.metaweather.com/api/location/search/?lattlong="
                + location.first + "," + location.second).responseObject(Response.Deserializer()) { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                }
                is Result.Success -> {
                    val data = result.get()
                    data.map { println(it) }
                }
            }
        }
    }
}