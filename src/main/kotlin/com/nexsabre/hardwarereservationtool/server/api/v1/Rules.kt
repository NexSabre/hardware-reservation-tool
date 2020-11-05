package com.nexsabre.hardwarereservationtool.server.api.v1

import com.nexsabre.hardwarereservationtool.server.configuration.Config
import com.nexsabre.hardwarereservationtool.server.configuration.Configuration
import com.sksamuel.hoplite.Masked
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/rules")
class Rules {
    private var config = Configuration().all()

    @GetMapping
    fun rules(): Config {
        config.mask()
        return config
    }
}

fun Config.mask() {
    this.rules.password = Masked("******")
}
