package com.nexsabre.hardwarereservationtool.cmd

import com.github.kittinunf.fuel.httpGet

class Hart() {
    private fun getResultsOrNull(string: String): String? {
        val response = string.httpGet().responseString().third
        return response.component1()
    }

    fun info(): String? {
        return getResultsOrNull(Endpoints().info)
    }
}