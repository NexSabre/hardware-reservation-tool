package com.nexsabre.hardwarereservationtool.cmd.menu

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.int
import com.nexsabre.hardwarereservationtool.cmd.helpers.checkMachineExistsById
import com.nexsabre.hardwarereservationtool.cmd.helpers.deleteAllMachines
import com.nexsabre.hardwarereservationtool.cmd.helpers.deleteMachine
import kotlin.system.exitProcess

class Delete : CliktCommand() {
    private val id by option("-i", "--id").int()
    private val all by option("--all").flag(default = false)

    override fun run() {
        when (all) {
            true -> deleteAllMachines()
            false -> id?.let { deleteOneMachine(it) }
        }
    }
}

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
