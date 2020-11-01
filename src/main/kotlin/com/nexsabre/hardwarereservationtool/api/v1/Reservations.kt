package com.nexsabre.hardwarereservationtool.api.v1

import com.nexsabre.hardwarereservationtool.controllers.ReservationMachine
import com.nexsabre.hardwarereservationtool.models.Element
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/reservations")
class Reservations {

    @GetMapping("/{machineId}")
    fun reservation(@PathVariable machineId: Int): ResponseEntity<Element> {
        return when(val machine: Element? = ReservationMachine().get(machineId)){
            null -> ResponseEntity.noContent().build()
            else -> ResponseEntity.ok().body(machine)
        }
    }

    @GetMapping
    fun reservations(): List<Element> {
        return transaction {
            ReservationMachine().all()
        }
    }
}
