package com.nexsabre.hardwarereservationtool.cmd.helpers

import com.nexsabre.hardwarereservationtool.cmd.models.Hart
import com.nexsabre.hardwarereservationtool.server.models.Element
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.system.exitProcess


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

fun createNewMachine(name: String, address: String) {
    val response = Hart().createMachine(name, address)
    if (response == null) {
        println("Something went wrong")
        exitProcess(0)
    }
    when (response) {
        true -> println("Creation of a new machine was successful")
        false -> println("Creation of a new machine failed")
    }
}