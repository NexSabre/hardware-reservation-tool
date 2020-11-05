package com.nexsabre.hardwarereservationtool.cmd

class Endpoints() {
    val info = "${baseBuilder()}/api/v1/info"

    private fun baseBuilder(base: String = "http://localhost:8080"): String {
        return base
    }
}


