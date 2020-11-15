package com.nexsabre.hardwarereservationtool.cmd

import com.github.ajalt.clikt.core.subcommands
import com.nexsabre.hardwarereservationtool.cmd.menu.*


fun main(array: Array<String>) {
    checkServiceIsAlive()
    HartCmd()
            .subcommands(
                    Create(),
                    Info(),
                    Machines(),
                    Reserve(),
                    Reservations(),
                    Status(),
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
