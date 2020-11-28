package com.nexsabre.hardwarereservationtool.server.api.v1

import com.nexsabre.hardwarereservationtool.server.requests.InfoData
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1")
class Info {

    @GetMapping("/info")
    @Operation(
            summary = "Return a dict with information about the status and the version of the service"
    )
    @ApiResponse(responseCode = "200", description = "Return a InfoData()")
    @Tag(name = "info")
    fun info(): InfoData {
        // TODO add status information later
        return InfoData("0.1", "Ok")
    }
}