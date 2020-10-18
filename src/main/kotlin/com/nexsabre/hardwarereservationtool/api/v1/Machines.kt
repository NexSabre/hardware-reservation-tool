package com.nexsabre.hardwarereservationtool.api.v1

import com.nexsabre.hardwarereservationtool.controllers.ReservationMachine
import com.nexsabre.hardwarereservationtool.controllers.allMachines
import com.nexsabre.hardwarereservationtool.models.Element
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

data class AddNewMachine(val name: String, val address: String)


@RestController
@RequestMapping("/api/v1/machines")
class Machines {
    @GetMapping
    fun getAllMachines(): List<Element> {
        return allMachines()
    }

    @PostMapping
    fun postAddMachine(@RequestBody newMachine: AddNewMachine): ResponseEntity<String> {
        return when(ReservationMachine()
                .create(name = newMachine.name, address = newMachine.address, null, null)) {
            true -> ResponseEntity(HttpStatus.CREATED)
            false -> ResponseEntity(HttpStatus.CONFLICT)
        }
    }

}
