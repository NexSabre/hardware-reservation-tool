package com.nexsabre.hardwarereservationtool.cmd.models

import com.github.kittinunf.fuel.core.extensions.jsonBody
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.nexsabre.hardwarereservationtool.cmd.data.ErrorData
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class ReservationRequest(val id: Int, val duration: Int)


class Hart {
    private fun postStatusCode(string: String,
                               jsonBody: String,
                               success: List<Int> = listOf(200..299).flatten(),
                               fail: List<Int> = listOf(400..499).flatten()): Boolean? {
        val response = string.httpPost().jsonBody(jsonBody).responseString().second
        return when (response.statusCode) {
            in success -> true
            in fail -> false
            else -> null
        }
    }

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

    fun reservations(): String? {
        return getResultsOrNull(Endpoints().reservations)
    }

    fun isAlive(): Boolean {
        if (this.getStatusCode(Endpoints().info) != 200) {
            return false
        }
        return true
    }

    fun reserve(machineId: Int): Boolean? {
        val obj = ReservationRequest(machineId, 2)
        val json = Json.encodeToString<ReservationRequest>(obj)
        val ss = mapOf(
                "json" to "something",
                "asdfd" to "asdfasdf"
        )
        postStatusCode(
                Endpoints().reserveMachineId(machineId),
                json
        )
        return true
    }

    fun release(machineId: Int): Boolean? {
        getStatusCode(Endpoints().releaseMachineId(machineId))
        return true
    }

}