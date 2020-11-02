package com.nexsabre.hardwarereservationtool.api.v1

import com.nexsabre.hardwarereservationtool.configuration.Config
import com.nexsabre.hardwarereservationtool.configuration.Configuration
//import com.nexsabre.hardwarereservationtool.configuration.RulesConfig
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/rules")
class Rules {
    // This endpoint should return information about all rules which restrict
    // user to reserve wrong machine
    private val config = Configuration()

    @GetMapping
    fun rules(): Config {
        return config.all()
    }
}