package com.nexsabre.hardwarereservationtool.cmd.models

import com.nexsabre.hardwarereservationtool.cmd.data.ApiVersion


class Endpoints(apiVersion: ApiVersion = ApiVersion.API_V1) {
    val info = "${baseBuilder()}${apiVersion.endpoint}info"

    private fun baseBuilder(base: String = "http://localhost:8080/"): String {
        return base
    }
}


