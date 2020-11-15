package com.nexsabre.hardwarereservationtool.cmd.helpers

import com.nexsabre.hardwarereservationtool.cmd.menu.deleteOneMachine
import com.nexsabre.hardwarereservationtool.cmd.models.Hart
import kotlin.system.exitProcess

fun deleteMachine(machineId: Int) {
    val response = Hart().deleteMachine(machineId)
    if (response == null) {
        println("Something went wrong")
        exitProcess(0)
    }
    println(response)
}

fun deleteAllMachines() {
    // TODO Later add a master password to remove an all machines
    getAllMachines()?.forEach {
        it.id?.let { id -> deleteOneMachine(id) }
    }
}