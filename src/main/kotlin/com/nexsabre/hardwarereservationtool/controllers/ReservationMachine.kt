package com.nexsabre.hardwarereservationtool.controllers

import com.nexsabre.hardwarereservationtool.models.Element
import com.nexsabre.hardwarereservationtool.models.Machine
import com.nexsabre.hardwarereservationtool.models.toElement
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

    fun get(machineId: Int): Element? {
        try {
            return transaction {
                return@transaction Machine.findById(machineId)?.toElement()
            }
        } catch (e: ExposedSQLException) {
        } catch (e: java.util.NoSuchElementException) {
            return null
        }
        return null
    }


    fun isAvailable(machineId: Int): Boolean {
        val machine = this.get(machineId) ?: return false
        if (machine.start != null) {
            return false
        } else if (machine.ends != null) {
            return false
        }
        return true
    }

    fun reserve(machineId: Int): Boolean {
        if (!this.isAvailable(machineId)) {
            return false
        }
        transaction {
            val machine = Machine.findById(machineId)
            machine?.let {
                it.reservationStart = DateTime.now()
            }
        }
        return true
    }

    fun release(machineId: Int): Boolean {
        if (this.isAvailable(machineId)) {
            return false
        }
        transaction {
            val machine: Machine? = Machine.findById(machineId)
            machine?.let {
                it.reservationStart = null
                it.reservationEnds = null
            }
        }
        return true
    }
}
