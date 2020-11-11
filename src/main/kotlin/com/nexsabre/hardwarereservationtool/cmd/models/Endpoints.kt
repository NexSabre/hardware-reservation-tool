package com.nexsabre.hardwarereservationtool.cmd.models

import com.nexsabre.hardwarereservationtool.cmd.data.ApiVersion


class Endpoints(private val apiVersion: ApiVersion = ApiVersion.API_V1) {
    val info = uriBuilder("info")
    val machines = uriBuilder("machines")
    val reserve = uriBuilder("reserve")
    val reservations = uriBuilder("reservations")

    fun reserveMachineId(id: Int) = uriBuilder("reserve/$id")
    fun releaseMachineId(id: Int) = uriBuilder("reserve/$id/release")

    fun machine(id: Int) = uriBuilder("machines/$id")

    private fun baseBuilder(base: String = "http://localhost:8080/"): String {
        return base
    }

    private fun uriBuilder(endpoint: String): String {
        return "${baseBuilder()}${apiVersion.endpoint}$endpoint"
    }


}


