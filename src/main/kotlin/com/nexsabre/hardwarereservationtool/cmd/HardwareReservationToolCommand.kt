package com.nexsabre.hardwarereservationtool.cmd

import com.github.ajalt.clikt.core.CliktCommand


class Info : CliktCommand() {
    override fun run() {
        println(Hart().info())
    }
}


fun main(array: Array<String>) {
    Info().main(array)
}