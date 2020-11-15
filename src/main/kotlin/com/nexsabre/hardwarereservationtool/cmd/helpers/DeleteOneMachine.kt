package com.nexsabre.hardwarereservationtool.cmd.helpers

import kotlin.system.exitProcess

fun deleteOneMachine(id: Int) {
    if (!checkMachineExistsById(id)) {
        println("Machine $id does not exist, please provide a correct ID")
        exitProcess(1)
    }
    deleteMachine(id)
    if (checkMachineExistsById(id)) {
        println("Machine $id was not removed")
        exitProcess(1)
    }
    println("Machine $id was successfully removed")
}
