package com.nexsabre.hardwarereservationtool.cmd.helpers

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
    val response = Hart().machines() ?: return null
    return Json.decodeFromString<List<Element>>(response).first()
}

fun getAllMachines(): List<Element>? {
    val response = Hart().machines() ?: return null
    return Json.decodeFromString<List<Element>>(response)
}

fun List<Element>?.prettyPrint() {
    if (this == null || this.isEmpty()) {
        println("0. \t Nothing to display")

    } else {
        this.forEachIndexed { index, element ->
            println("${index + 1}. $element")
        }
    }
}

fun Element?.prettyPrint() {
    if (this == null) {
        println("0. \t Nothing to display")
    } else {
        println("1. \t $this")
    }
}