package com.mytaxi.demo

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.result.Result
import com.google.gson.Gson
import com.mytaxi.demo.model.Response
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DemoApplication

fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
    val apiClient = ApiClient()
    //API CALL #1
    apiClient.getWeatherInfo("Bordeaux")
    //API CALL #2
    apiClient.getWeatherByGps(Pair(45.491898, 9.182053)).map { println(it) }

}


class ApiClient {

    companion object {
        const val BASE_URL = "https://www.metaweather.com/api/location/search/"
    }

    /*This method allows to check the option from Fuel responseObject
        with the response Deserializer  */
    fun getWeatherInfo(query: String) {
        Fuel.get(BASE_URL + "?query="
                + query).responseObject(Response.Deserializer()) { _, _, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    println(ex)
                }
                is Result.Success -> {
                    result.get().map { println(it.title + " - " + it.woeid) }

                }
            }
        }
    }

    /*This method allows to check the option from Fuel responseString()
    to manually handle the result of api call as String */
    fun getWeatherByGps(location: Pair<Double, Double>): Array<Response> {
        val (_, _, result) = Fuel.get(BASE_URL + "?lattlong="
                + location.first + "," + location.second).responseString()
        /*Manual deserialization */
        return Gson().fromJson(result.component1(), Array<Response>::class.java)
    }
}
