package com.nexsabre.hardwarereservationtool.controllers

import com.nexsabre.hardwarereservationtool.models.Element
import com.nexsabre.hardwarereservationtool.models.Machine
import org.jetbrains.exposed.exceptions.ExposedSQLException
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime


class ReservationMachine {
    fun create(name: String, address: String, start: DateTime?, ends: DateTime?) {
        try {
            transaction {
                Machine.new {
                    this.name = name
                    this.address = address
                    this.reservationStart = start
                    this.reservationEnds = ends
                }
            }
        } catch (e: ExposedSQLException) {
            println("Cannot create a new Machine($name, $address, $start, $ends)")
        }
    }

    fun create(machines: List<Element>) {
        for (machine in machines) {
            create(machine.name!!, machine.address!!, machine.start, machine.ends)
        }
    }
}
