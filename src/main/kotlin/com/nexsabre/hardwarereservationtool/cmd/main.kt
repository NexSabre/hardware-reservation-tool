package com.nexsabre.hardwarereservationtool.cmd

import com.github.ajalt.clikt.core.subcommands
import com.nexsabre.hardwarereservationtool.cmd.menu.HartCmd
import com.nexsabre.hardwarereservationtool.cmd.menu.Info
import com.nexsabre.hardwarereservationtool.cmd.menu.Machines


fun main(array: Array<String>) {
    checkServiceIsAlive()
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
