package com.nexsabre.hardwarereservationtool.api.v1

import com.nexsabre.hardwarereservationtool.configuration.Configuration
import com.nexsabre.hardwarereservationtool.controllers.ReservationMachine
import com.nexsabre.hardwarereservationtool.controllers.allMachines
import com.nexsabre.hardwarereservationtool.models.Element
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

data class AddMachine(val name: String, val address: String, val enabled: Boolean = true)

data class PasswordBodyRequest(val password: String)

@RestController
@RequestMapping("/api/v1/machines")
class Machines {
    @GetMapping
    fun getAllMachines(): List<Element> {
        return allMachines()
    }

    @RequestMapping(method = [RequestMethod.POST], consumes = ["application/json"])
    fun postAddMachine(@RequestBody machine: AddMachine): ResponseEntity<String> {
        val status = ReservationMachine().create(name = machine.name, address = machine.address, null, null)
        return when (status.first) {
            true -> ResponseEntity.created(URI("${status.second.id}")).body(Json.encodeToString(status.second))
            false -> ResponseEntity(HttpStatus.CONFLICT)
        }
    }

    @GetMapping("/{machineId}")
    fun getSpecificMachine(@PathVariable machineId: Int): ResponseEntity<Element?> {
        val specificMachine = ReservationMachine().get(machineId) ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        return ResponseEntity.ok().body(specificMachine)
    }

    @GetMapping("/{machineId}/delete")
    fun deleteMachine(@PathVariable machineId: Int): ResponseEntity<String> {
        return when (ReservationMachine().delete(machineId)) {
            true -> ResponseEntity(HttpStatus.NO_CONTENT)
            false -> ResponseEntity(HttpStatus.OK)
        }
    }

    @GetMapping("/{machineId}/enable")
    fun enableMachine(@PathVariable machineId: Int): ResponseEntity<String> {
        return when (ReservationMachine().enable(machineId)) {
            true -> ResponseEntity(HttpStatus.OK)
            false -> ResponseEntity(HttpStatus.CONFLICT)
        }
    }

    @GetMapping("/{machineId}/disable")
    fun disableMachine(@PathVariable machineId: Int): ResponseEntity<String> {
        return when (ReservationMachine().disable(machineId)) {
            true -> ResponseEntity(HttpStatus.OK)
            false -> ResponseEntity(HttpStatus.CONFLICT)
        }
    }

    @PostMapping("/{machineId}/protect", consumes = ["application/json"])
    fun protectMachine(@PathVariable machineId: Int, @RequestBody passwordRequest: PasswordBodyRequest):
            ResponseEntity<String> {
        if (!Configuration().checkPassword(passwordRequest.password)) {
            return ResponseEntity(HttpStatus.UNAUTHORIZED)
        }
        return when (ReservationMachine().protect(machineId)) {
            true -> ResponseEntity(HttpStatus.OK)
            false -> ResponseEntity(HttpStatus.CONFLICT)
        }
    }

    @PostMapping("/{machineId}/unprotect", consumes = ["application/json"])
    fun unprotectMachine(@PathVariable machineId: Int, @RequestBody passwordRequest: PasswordBodyRequest):
            ResponseEntity<String> {
        if (!Configuration().checkPassword(passwordRequest.password)) {
            return ResponseEntity(HttpStatus.UNAUTHORIZED)
        }
        return when (ReservationMachine().unprotect(machineId)) {
            true -> ResponseEntity(HttpStatus.OK)
            false -> ResponseEntity(HttpStatus.CONFLICT)
        }
    }
}
