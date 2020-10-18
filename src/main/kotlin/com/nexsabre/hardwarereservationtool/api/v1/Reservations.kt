package com.nexsabre.hardwarereservationtool.api.v1

import com.nexsabre.hardwarereservationtool.models.Element
import com.nexsabre.hardwarereservationtool.models.Machine
import com.nexsabre.hardwarereservationtool.models.toElement
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/reservations")
class Reservations {

    @GetMapping
    fun reservations(): List<Element> {
        return transaction {
            val allMachines = Machine.all().map {
                it.toElement()
            }
            allMachines
        }
    }
}
