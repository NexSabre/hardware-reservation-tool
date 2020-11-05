package com.nexsabre.hardwarereservationtool.cmd

import com.github.ajalt.clikt.core.CliktCommand
import com.nexsabre.hardwarereservationtool.cmd.models.Hart


class Info : CliktCommand() {
    override fun run() {
        println(Hart().info())
    }
}


fun main(array: Array<String>) {
    Info().main(array)
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
