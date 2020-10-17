package com.nexsabre.hardwarereservationtool.api.v1

import com.nexsabre.hardwarereservationtool.models.Machine
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

data class Element(val id: Int?, val name: String?, val address: String?, val start: Any?, val ends: Any?)

@RestController
@RequestMapping("/api/v1/reservations")
class Reservations {

    @GetMapping
    fun reservations(): List<Element> {
        return transaction {
            val allMachines = Machine.all().map {
                Element(it.id.value, it.name, it.address, it.reservationStart, it.reservationEnds)
            }
            allMachines
        }
    }

    @GetMapping("/reserve/{machineId}")
    fun reserve(@PathVariable machineId: String): String {
        return machineId
    }
}