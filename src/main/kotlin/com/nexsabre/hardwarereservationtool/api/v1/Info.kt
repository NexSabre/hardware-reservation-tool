package com.nexsabre.hardwarereservationtool.api.v1

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

data class InfoData(val version: String, val status: String)

@RestController
@RequestMapping("/api/v1")
class Info {

    @GetMapping("/info")
    fun info(): InfoData {
        // TODO add status information later
        return InfoData("0.1", "Ok")
    }
}