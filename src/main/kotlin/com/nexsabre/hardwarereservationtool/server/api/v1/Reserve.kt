package com.nexsabre.hardwarereservationtool.server.api.v1

import com.nexsabre.hardwarereservationtool.server.configuration.Configuration
import com.nexsabre.hardwarereservationtool.server.controllers.ReservationMachine
import com.nexsabre.hardwarereservationtool.server.models.Element
import com.nexsabre.hardwarereservationtool.server.models.Machine
import com.nexsabre.hardwarereservationtool.server.models.toElement
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

data class ReservationRequest(val id: Int, val start: Any?, val duration: Int, val password: String?)


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

    @PostMapping("/reserve/{machineId}", consumes = ["application/json"])
    fun postReserveMachine(@PathVariable machineId: Int, @RequestBody requestsBody: ReservationRequest): ResponseEntity<Unit> {
        // TODO implement a time, after the machine will be free

        if (ReservationMachine().protected(machineId) != null && ReservationMachine().protected(machineId)!!) {
            if (requestsBody.password == null || !Configuration().checkPassword(requestsBody.password)) {
                return ResponseEntity(HttpStatus.UNAUTHORIZED)
            }
        }

        return when (ReservationMachine().reserve(machineId, requestsBody.duration)) {
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
