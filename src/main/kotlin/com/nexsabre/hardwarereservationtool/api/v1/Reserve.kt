package com.nexsabre.hardwarereservationtool.api.v1

import com.nexsabre.hardwarereservationtool.controllers.ReservationMachine
import com.nexsabre.hardwarereservationtool.models.Element
import com.nexsabre.hardwarereservationtool.models.Machine
import com.nexsabre.hardwarereservationtool.models.toElement
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

data class ReservationRequest(val id: Int, val start: Any?, val duration: Number)


@RestController
@RequestMapping("/api/v1")
class Reserve {

    @GetMapping("/reserve/{machineId}")
    fun getReserveMachine(@PathVariable machineId: Int): ResponseEntity<Element>? {
        return transaction {
            Machine.findById(machineId)?.let {
                return@transaction ResponseEntity.ok().body(it.toElement())
            }
            return@transaction ResponseEntity.notFound().build()
        }
    }

    @PostMapping("/reserve/{machineId}")
    fun postReserveMachine(@PathVariable machineId: Int, @RequestBody requestBody: ReservationRequest): ResponseEntity<Unit> {
        return when (ReservationMachine().reserve(machineId)) {
            true -> ResponseEntity.ok().build()
            false -> ResponseEntity.notFound().build()
        }
    }

    @GetMapping("/reserve/{machineId}/release")
    fun getReleaseMachine(@PathVariable machineId: Int): ResponseEntity<Unit> {
        return when (ReservationMachine().release(machineId)) {
            true -> ResponseEntity.ok().build()
            false -> ResponseEntity.noContent().build()
        }
    }
}
