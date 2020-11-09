package com.nexsabre.hardwarereservationtool.cmd.models

import com.nexsabre.hardwarereservationtool.cmd.data.ApiVersion


class Endpoints(private val apiVersion: ApiVersion = ApiVersion.API_V1) {
    val health = uriBuilder("health")
    val info = uriBuilder("info")
    val machines = uriBuilder("machines")
    val reserve = uriBuilder("reserve")
    val reservations = uriBuilder("reservations")

    private fun baseBuilder(base: String = "http://localhost:8080/"): String {
        return base
    }

    private fun uriBuilder(endpoint: String): String {
        return "${baseBuilder()}${apiVersion.endpoint}$endpoint"
    }
}


