package com.nexsabre.hardwarereservationtool.cmd.helpers.reserve

import com.nexsabre.hardwarereservationtool.cmd.models.Hart

fun reserve(machineId: Int): Boolean {
    val operation = Hart().reserve(machineId) ?: return false
    if (!operation) {
        println("Reservation of id: $machineId failed")
        return false
    }
    println("Reservation of id: $machineId success")
    return true
}

fun release(machineId: Int): Boolean {
    val operationStatus = Hart().release(machineId) ?: return false
    if (!operationStatus) {
        println("Release of id: $machineId failed")
        return false
    }
    println("Release of id: $machineId success")
    return true
}

fun protect(machineId: Int, passwordInLine: String): Boolean {
    val operationStatus = Hart().protect(machineId, passwordInLine) ?: return false
    if (!operationStatus) {
        println("Protect of id: $machineId failed")
        return false
    }
    println("Protect of id: $machineId success")
    return true
}

fun unprotect(machineId: Int, passwordInLine: String): Boolean {
    val operationStatus = Hart().unprotect(machineId, passwordInLine) ?: return false
    if (!operationStatus) {
        println("Unprotect of id: $machineId failed")
        return false
    }
    println("Unprotect of id: $machineId success")
    return true
}
