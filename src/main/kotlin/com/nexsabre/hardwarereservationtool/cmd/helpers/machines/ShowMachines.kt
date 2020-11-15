package com.nexsabre.hardwarereservationtool.cmd.helpers.machines

import com.nexsabre.hardwarereservationtool.cmd.helpers.prettyPrint
import kotlin.system.exitProcess

fun showMachines(id: Int) {
    if (id < 0) {
        getAllMachines().prettyPrint()
        exitProcess(0)
    }
    val check = checkMachineExistsById(id)
    if (!check) {
        println("Machine $id does not exist. Please provide a proper machine id.")
        exitProcess(0)
    }
    getMachineById(id).prettyPrint()
}

