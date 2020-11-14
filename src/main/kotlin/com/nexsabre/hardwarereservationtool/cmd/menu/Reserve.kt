package com.nexsabre.hardwarereservationtool.cmd.menu

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.switch
import com.github.ajalt.clikt.parameters.types.int
import com.nexsabre.hardwarereservationtool.cmd.helpers.protect
import com.nexsabre.hardwarereservationtool.cmd.helpers.release
import com.nexsabre.hardwarereservationtool.cmd.helpers.reserve
import com.nexsabre.hardwarereservationtool.cmd.helpers.unprotect
import kotlin.system.exitProcess

class Reserve : CliktCommand() {
    private val machineId by option("-i").int()
    private val releaseFlag by option("-r", "--release").flag(default = false)

    private val protectFlag by option().switch(
            "--protect" to 1,
            "--unprotect" to 0
    ).default(-1)

    private val passwordInLine by option("-p", "--password").default("")

    override fun run() {
        if (machineId == null) {
            println("Please provide a machine id")
            exitProcess(0)
        }
        if (releaseFlag) {
            machineId?.let { release(it) }
        } else {
            machineId?.let { reserve(it) }
        }

        when (protectFlag) {
            1 -> protect(machineId!!, passwordInLine)
            0 -> unprotect(machineId!!, passwordInLine)
        }
    }
}
