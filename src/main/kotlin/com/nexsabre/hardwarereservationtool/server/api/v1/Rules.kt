package com.nexsabre.hardwarereservationtool.server.api.v1

import com.nexsabre.hardwarereservationtool.server.configuration.Config
import com.nexsabre.hardwarereservationtool.server.configuration.Configuration
import com.nexsabre.hardwarereservationtool.server.helpers.mask
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/rules")
class Rules {
    private var config = Configuration().all()

    @GetMapping
    @Operation(
            summary = "Return a dict with rules for this specific instance",
            description = "It also contains a information about database and configuration for the server"
    )
    @Tag(name = "rules")
    fun rules(): Config {
        config.mask()
        return config
    }
}
