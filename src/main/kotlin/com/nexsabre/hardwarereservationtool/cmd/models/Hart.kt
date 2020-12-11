package com.nexsabre.hardwarereservationtool.cmd.models

import com.github.kittinunf.fuel.core.extensions.jsonBody
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.nexsabre.hardwarereservationtool.cmd.data.ErrorData
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


class Hart {
    private fun postStatusCode(string: String,
                               jsonBody: String,
                               success: List<Int> = listOf(200..299).flatten(),
                               fail: List<Int> = listOf(400..599).flatten()): Boolean? {
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

    private fun getStatusCodeAndValidate(string: String,
                                         success: List<Int> = listOf(200..299).flatten(),
                                         fail: List<Int> = listOf(400..599).flatten()): Boolean? {
        val response = string.httpGet().responseString().second
        return when (response.statusCode) {
            in success -> true
            in fail -> false
            else -> null
        }
    }

    private fun getResultsOrNull(string: String): String? {
        val response = string.httpGet().responseString().third
        return response.component1() ?: ErrorData("no data", "cannot connect to the service").toString()
    }

    fun info(): String? {
        return getResultsOrNull(Endpoints().info)
    }

    fun machine(machineId: Int): String? {
        return getResultsOrNull(Endpoints().machine(machineId))
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

    fun reserve(machineId: Int, duration: Int = 0): Boolean? {
        val requestValue = mapOf(
                "id" to machineId,
                "duration" to duration
        )
        val jsonBody = Json.encodeToString(requestValue)
        return postStatusCode(
                Endpoints().reserveMachineId(machineId),
                jsonBody
        )
    }

    fun release(machineId: Int): Boolean? {
        return getStatusCodeAndValidate(Endpoints().releaseMachineId(machineId))
    }

    private fun changeProtectStatus(machineId: Int, protect: Boolean = true): String {
        return when (protect) {
            true -> Endpoints().machineProtect(machineId)
            false -> Endpoints().machineUnprotect(machineId)
        }
    }

    fun protect(machineId: Int, passwordInLine: String): Boolean? {
        return this.postStatusCode(
                this.changeProtectStatus(machineId),
                Json.encodeToString(mapOf("password" to passwordInLine))
        )
    }

    fun unprotect(machineId: Int, passwordInLine: String): Boolean? {
        return this.postStatusCode(
            this.changeProtectStatus(machineId, protect = false),
            Json.encodeToString(mapOf("password" to passwordInLine))
        )
    }

    fun createMachine(name: String, address: String): Boolean? {
        // enabled: Boolean = true, protected: Boolean = false
        val data = mapOf(
            "name" to name,
            "address" to address
        )
        return this.postStatusCode(
            Endpoints().machines,
            Json.encodeToString(data),
            success = listOf(201)
        )
    }

    fun deleteMachine(machineId: Int): String? {
        return getResultsOrNull(Endpoints().machineDelete(machineId))
    }
}
