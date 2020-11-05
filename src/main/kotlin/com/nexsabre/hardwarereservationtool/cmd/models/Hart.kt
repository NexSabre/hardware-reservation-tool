package com.nexsabre.hardwarereservationtool.cmd.models

import com.github.kittinunf.fuel.httpGet
import com.nexsabre.hardwarereservationtool.cmd.data.ErrorData

class Hart {
    private fun getResultsOrNull(string: String): String? {
        val response = string.httpGet().responseString().third
        return response.component1() ?: ErrorData("no data", "cannot connect to the service").toString()
    }

    fun info(): String? {
        return getResultsOrNull(Endpoints().info)
    }
}