package com.nexsabre.hardwarereservationtool.server.api.v1

import com.nexsabre.hardwarereservationtool.server.configuration.Configuration
import com.nexsabre.hardwarereservationtool.server.controllers.ReservationMachine
import com.nexsabre.hardwarereservationtool.server.controllers.allMachines
import com.nexsabre.hardwarereservationtool.server.models.Element
import com.nexsabre.hardwarereservationtool.server.requests.AddMachineRequest
import com.nexsabre.hardwarereservationtool.server.requests.PasswordBodyRequest
import io.swagger.v3.oas.annotations.Operation
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/api/v1/machines")
class Machines {
    @GetMapping
    @Operation(summary = "Return a list of all devices")
    fun getAllMachines(): List<Element> {
        return allMachines()
    }

    @RequestMapping(method = [RequestMethod.POST], consumes = ["application/json"])
    @Operation(summary = "Create a new machine")
    fun postAddMachine(@RequestBody machine: AddMachineRequest): ResponseEntity<String> {
        val status = ReservationMachine().create(name = machine.name, address = machine.address, null, null)
        return when (status.first) {
            true -> ResponseEntity.created(URI("${status.second.id}")).body(Json.encodeToString<Element>(status.second))
            false -> ResponseEntity(HttpStatus.CONFLICT)
        }
    }

    @GetMapping("/{machineId}")
    @Operation(summary = "Get detailed information about a specific machine")
    fun getSpecificMachine(@PathVariable machineId: Int): ResponseEntity<Element?> {
        val specificMachine = ReservationMachine().get(machineId) ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        return ResponseEntity.ok().body(specificMachine)
    }

    @GetMapping("/{machineId}/delete")
    @Operation(summary = "Delete a machine with specific ID")
    fun deleteMachine(@PathVariable machineId: Int): ResponseEntity<String> {
        return when (ReservationMachine().delete(machineId)) {
            true -> ResponseEntity(HttpStatus.NO_CONTENT)
            false -> ResponseEntity(HttpStatus.OK)
        }
    }

    @GetMapping("/{machineId}/enable")
    @Operation(summary = "Enable machine for reservation operation",
            description = "When user create a new machine, by default it is \'enable\'")
    fun enableMachine(@PathVariable machineId: Int): ResponseEntity<String> {
        return when (ReservationMachine().enable(machineId)) {
            true -> ResponseEntity(HttpStatus.OK)
            false -> ResponseEntity(HttpStatus.CONFLICT)
        }
    }

    @GetMapping("/{machineId}/disable")
    @Operation(summary = "Disable machine for reservation operation",
            description = "Disabled machine can signalize, it is under maintenance")
    fun disableMachine(@PathVariable machineId: Int): ResponseEntity<String> {
        return when (ReservationMachine().disable(machineId)) {
            true -> ResponseEntity(HttpStatus.OK)
            false -> ResponseEntity(HttpStatus.CONFLICT)
        }
    }

    @PostMapping("/{machineId}/protect", consumes = ["application/json"])
    @Operation(summary = "Set a machine in protected state",
            description = "If enabled, the only way to reserve it is using a request with master password")
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
    @Operation(summary = "Release a machine from the protected state",
            description = "No password is needed to reserve a machine")
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
