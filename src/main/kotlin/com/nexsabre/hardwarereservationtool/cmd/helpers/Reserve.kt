package com.nexsabre.hardwarereservationtool.cmd.helpers

import com.nexsabre.hardwarereservationtool.cmd.models.Hart

fun reserve(machineId: Int): Boolean {
    val response = Hart().reserve(machineId) ?: return false
    return true
}

fun release(machineId: Int): Boolean {
    val response = Hart().release(machineId) ?: return false
    return true
}

