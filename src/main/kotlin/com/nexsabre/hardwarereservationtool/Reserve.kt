package com.nexsabre.hardwarereservationtool

import com.nexsabre.hardwarereservationtool.api.v1.Element
import com.nexsabre.hardwarereservationtool.models.Machine
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

fun Machine.toElement() = Element(
        id = id.value,
        name = name,
        address = address,
        start = reservationStart,
        ends = reservationEnds
)

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

    @PostMapping("/reserve/{machineId")
    fun postReserveMachine(@PathVariable machineId: Int): ResponseEntity<Element> {
        return transaction {
            Machine.findById(machineId)?.let {
                return@transaction ResponseEntity.ok().body(it.toElement())
            }
            return@transaction ResponseEntity(HttpStatus.NOT_FOUND)

        }
    }

}