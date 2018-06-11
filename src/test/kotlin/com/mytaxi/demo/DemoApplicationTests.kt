package com.mytaxi.demo

import org.junit.Test
import org.junit.Assert.*
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class DemoApplicationTests {
    companion object {
        val BARCELONA_COORDINATES = Pair(41.3851, 2.1734)
    }

    @Test
    fun shouldGetWeatherBasedOnGps() {
        val apiClient = ApiClient()
        val expectedString = "Barcelona"
        assertEquals(apiClient.getWeatherByGps(BARCELONA_COORDINATES)[0].title, expectedString)
    }

}
