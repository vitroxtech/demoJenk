package com.mytaxi.demo

import org.junit.Test
import org.junit.Assert.*
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class DemoApplicationTests {

    @Test
    fun shouldGetWeatherBasedOnGps() {
        val apiClient = ApiClient()
        assertEquals(apiClient.getWeatherByGps(Pair(41.3851, 2.1734))[0].title, "Barcelona")
    }

}
