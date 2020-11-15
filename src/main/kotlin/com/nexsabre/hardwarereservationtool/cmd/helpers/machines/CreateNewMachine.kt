package com.nexsabre.hardwarereservationtool.cmd.helpers.machines

import com.nexsabre.hardwarereservationtool.cmd.models.Hart
import kotlin.system.exitProcess

fun createNewMachine(name: String, address: String) {
    val response = Hart().createMachine(name, address)
    if (response == null) {
        println("Something went wrong")
        exitProcess(0)
    }
    when (response) {
        true -> println("Creation of a new machine was successful")
        false -> println("Creation of a new machine failed")
    }
}
