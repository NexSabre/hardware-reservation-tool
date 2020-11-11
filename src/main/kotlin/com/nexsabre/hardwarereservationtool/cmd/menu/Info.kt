package com.nexsabre.hardwarereservationtool.cmd.menu

import com.github.ajalt.clikt.core.CliktCommand
import com.nexsabre.hardwarereservationtool.cmd.models.Hart

class Info : CliktCommand() {
    override fun run() {
        println(Hart().info())
    }
}