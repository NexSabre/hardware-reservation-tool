package com.nexsabre.hardwarereservationtool.cmd

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands
import com.nexsabre.hardwarereservationtool.cmd.models.Hart


class HartCmd : CliktCommand() {
    override fun run() = Unit
}

class Info : CliktCommand() {
    override fun run() {
        println(Hart().info())
    }
}

class Machines : CliktCommand() {
    val helpMessage = "Help message"
    override fun getFormattedHelp(): String {
        return "asdasdasdasd"
    }

    override fun run() {
        TODO("Not yet implemented")
    }
}

fun main(array: Array<String>) {
    HartCmd()
            .subcommands(
                    Info(),
                    Machines()
            )
            .main(array)
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
