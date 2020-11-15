package com.nexsabre.hardwarereservationtool.cmd.menu

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.int
import com.nexsabre.hardwarereservationtool.cmd.helpers.machines.deleteAllMachines
import com.nexsabre.hardwarereservationtool.cmd.helpers.machines.deleteOneMachine

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

