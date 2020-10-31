package com.nexsabre.hardwarereservationtool.api.v1

import com.nexsabre.hardwarereservationtool.controllers.ReservationMachine
import com.nexsabre.hardwarereservationtool.controllers.allMachines
import com.nexsabre.hardwarereservationtool.models.Element
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

data class AddMachine(val name: String, val address: String)

@RestController
@RequestMapping("/api/v1/machines")
class Machines {
    @GetMapping
    fun getAllMachines(): List<Element> {
        return allMachines()
    }

//    @PostMapping
    @RequestMapping(method = [RequestMethod.POST], consumes = ["application/json"])
    fun postAddMachine(@RequestBody machine: AddMachine): ResponseEntity<String> {
        return when(ReservationMachine()
                .create(name = machine.name, address = machine.address, null, null)) {
            true -> ResponseEntity(HttpStatus.CREATED)
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
        return when(ReservationMachine().delete(machineId)) {
            true -> ResponseEntity(HttpStatus.NO_CONTENT)
            false -> ResponseEntity(HttpStatus.OK)
        }
    }
}
