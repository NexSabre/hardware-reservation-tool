package com.nexsabre.hardwarereservationtool.cmd.helpers

import com.nexsabre.hardwarereservationtool.cmd.models.Hart
import kotlin.system.exitProcess

fun checkServiceIsAlive() {
    if (!Hart().isAlive()) {
        println("Service is not available or cmd's app can not connect to it.")
        exitProcess(1)
    }
}