package com.nexsabre.hardwarereservationtool.cmd.helpers.machines

import com.nexsabre.hardwarereservationtool.cmd.models.Hart
import com.nexsabre.hardwarereservationtool.server.models.Element
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json


fun checkMachineExistsById(machineId: Int): Boolean {
    val response = Hart().machines() ?: return false
    val machines = Json.decodeFromString<List<Element>>(response)

    machines.firstOrNull {
        it.id == machineId
    } ?: return false
    return true
}

fun getMachineById(machineId: Int): Element? {
    val response = Hart().machine(machineId) ?: return null
    return Json.decodeFromString<Element>(response)
}

fun getAllMachines(): List<Element>? {
    val response = Hart().machines() ?: return null
    return Json.decodeFromString<List<Element>>(response)
}
