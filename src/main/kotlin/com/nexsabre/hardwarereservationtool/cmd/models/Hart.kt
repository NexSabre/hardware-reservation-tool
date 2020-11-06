package com.nexsabre.hardwarereservationtool.cmd.models

import com.github.kittinunf.fuel.httpGet
import com.nexsabre.hardwarereservationtool.cmd.data.ErrorData

class Hart {
    private fun getStatusCode(string: String): Int {
        val response = string.httpGet().responseString().second
        return response.statusCode
    }

    private fun getResultsOrNull(string: String): String? {
        val response = string.httpGet().responseString().third
        return response.component1() ?: ErrorData("no data", "cannot connect to the service").toString()
    }

    fun info(): String? {
        return getResultsOrNull(Endpoints().info)
    }

    fun machines(): String? {
        return getResultsOrNull(Endpoints().machines)
    }

    fun isAlive(): Boolean {
        if (this.getStatusCode(Endpoints().info) != 200) {
            return false
        }
        return true
    }

}