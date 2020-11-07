package com.nexsabre.hardwarereservationtool.cmd

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.arguments.default
import com.github.ajalt.clikt.parameters.options.counted
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.switch
import com.github.ajalt.clikt.parameters.types.int
import com.nexsabre.hardwarereservationtool.cmd.helpers.checkMachineExistsById
import com.nexsabre.hardwarereservationtool.cmd.helpers.getAllMachines
import com.nexsabre.hardwarereservationtool.cmd.helpers.getMachineById
import com.nexsabre.hardwarereservationtool.cmd.helpers.prettyPrint
import com.nexsabre.hardwarereservationtool.cmd.models.Hart
import kotlin.system.exitProcess


class HartCmd : CliktCommand() {
    override fun run() = Unit
}

class Info : CliktCommand() {
    override fun run() {
        println(Hart().info())
    }
}

class Machines : CliktCommand() {
    private val id: Int by argument().int().default(-1)

    private val chooseProtect by option().switch(
            "--protect" to 1,
            "--unprotect" to 0
    ).default(-1)

    private val chooseEnable by option().switch(
            "--enable" to 1,
            "--disable" to 0
    ).default(-1)

    private val verbosity by option("-v").counted()

    override fun run() {
        if (verbosity > 0) {
            println("Configuration:" +
                    "\nMachine ID: $id" +
                    "\nProtect: $chooseProtect" +
                    "\nEnable:  $chooseEnable" +
                    "\nVerbosity lvl: $verbosity")
        }
        if (chooseProtect == -1 && chooseEnable == -1) {
            showMachines(id)
        }
    }
}

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

fun main(array: Array<String>) {
    checkServiceIsAlive()
    HartCmd()
            .subcommands(
                    Info(),
                    Machines()
            )
            .main(array)
}

fun checkServiceIsAlive() {
    if (!Hart().isAlive()) {
        println("Service is not available or cmd's app can not connect to it.")
    }
}

//* main
//        * info
//        * configuration
//            --set-server
//            --set-password [optional]
//        * machine
//            --protect
//            --unprotect
//            --enable
//            --disable
