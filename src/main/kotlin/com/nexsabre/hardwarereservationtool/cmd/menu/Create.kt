package com.nexsabre.hardwarereservationtool.cmd.menu

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import com.nexsabre.hardwarereservationtool.cmd.helpers.machines.createNewMachine
import kotlin.system.exitProcess

class Create : CliktCommand() {
    private val name by option("-n", "--name")
    private val address by option("-a", "--address")
    private val enabled by option("-e", "--enabled").flag(default = true)
    private val protected by option("-p", "--protected").flag(default = false)

    override fun run() {
        if (name == null) {
            println("Name is required")
            exitProcess(0)
        }
        if (address == null) {
            println("Address is required")
            exitProcess(0)
        }
        createNewMachine(name!!, address!!)
    }
}
