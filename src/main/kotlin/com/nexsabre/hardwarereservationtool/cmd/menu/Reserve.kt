package com.nexsabre.hardwarereservationtool.cmd.menu

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.int
import com.nexsabre.hardwarereservationtool.cmd.helpers.release
import com.nexsabre.hardwarereservationtool.cmd.helpers.reserve

class Reserve : CliktCommand() {
    val machineId by option("-i").int()
    val releaseFlag by option("-r", "--release").flag(default = false)

    override fun run() {
        if (releaseFlag) {
            machineId?.let { release(it) }
        } else {
            machineId?.let { reserve(it) }
        }
    }
}
