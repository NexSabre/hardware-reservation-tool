package com.nexsabre.hardwarereservationtool.server.api.v1

import com.nexsabre.hardwarereservationtool.server.controllers.ReservationMachine
import com.nexsabre.hardwarereservationtool.server.models.Element
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/v1/reservations")
class Reservations {

    @GetMapping("/{machineId}")
    fun reservation(@PathVariable machineId: Int): ResponseEntity<Element> {
        return when (val machine: Element? = ReservationMachine().get(machineId)) {
            null -> ResponseEntity.noContent().build()
            else -> ResponseEntity.ok().body(machine)
        }
    }

    @GetMapping
    fun reservations(@RequestParam available: Boolean = false): List<Element> {
        return transaction {
            val allEnabled = ReservationMachine().all().filter {
                it.enabled
            }
            if (available) {
                return@transaction allEnabled.filter {
                    it.start == null
                }
            }
            return@transaction allEnabled
        }
    }
}
