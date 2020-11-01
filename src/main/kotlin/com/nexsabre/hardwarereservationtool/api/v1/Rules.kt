package com.nexsabre.hardwarereservationtool.api.v1

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/rules")
class Rules {
    // This endpoint should return information about all rules which restrict
    // user to reserve wrong machine
}