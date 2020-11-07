package com.nexsabre.hardwarereservationtool.cmd

import com.nexsabre.hardwarereservationtool.cmd.helpers.checkMachineExistsById
import com.nexsabre.hardwarereservationtool.cmd.helpers.getAllMachines
import com.nexsabre.hardwarereservationtool.cmd.helpers.getMachineById
import com.nexsabre.hardwarereservationtool.cmd.helpers.prettyPrint
import com.nexsabre.hardwarereservationtool.cmd.models.Hart
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

fun checkServiceIsAlive() {
    if (!Hart().isAlive()) {
        println("Service is not available or cmd's app can not connect to it.")
    }
}