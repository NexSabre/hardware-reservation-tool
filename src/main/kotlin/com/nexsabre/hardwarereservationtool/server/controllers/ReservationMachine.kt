package com.nexsabre.hardwarereservationtool.server.controllers

import com.nexsabre.hardwarereservationtool.server.models.Element
import com.nexsabre.hardwarereservationtool.server.models.Machine
import com.nexsabre.hardwarereservationtool.server.models.toElement
import org.jetbrains.exposed.exceptions.ExposedSQLException
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.Instant

fun allMachines(): List<Element> {
    return transaction {
        return@transaction Machine.all().map { it.toElement() }
    }
}


class ReservationMachine {
    fun all(): List<Element> {
        return transaction {
            return@transaction Machine.all().map { it.toElement() }
        }
    }

    fun create(name: String, address: String, start: Long?, ends: Long?, enabled: Boolean = true): Pair<Boolean, Element> {
        val machinesBefore = all()
        var status: Boolean
        try {
            status = transaction {
                val machinesBeforeNewOne = Machine.all().map { it.toElement() }.size
                Machine.new {
                    this.name = name
                    this.address = address
                    this.reservationStart = start
                    this.reservationEnds = ends
                    this.enabled = enabled
                }

                val machinesAfterAddingNewOne = Machine.all().map { it.toElement() }.size
                return@transaction machinesAfterAddingNewOne != machinesBeforeNewOne
            }
        } catch (e: ExposedSQLException) {
            println("Cannot create a new Machine($name, $address, $start, $ends)")
            status = false
        }
        val newMachine = (all() - machinesBefore).first()
        return Pair(status, newMachine)
    }

    fun create(machines: List<Element>) {
        for (machine in machines) {
            create(machine.name!!, machine.address!!, machine.start, machine.ends, machine.enabled)
        }
    }

    fun delete(machineId: Int): Boolean {
        try {
            return transaction {
                val machinesBeforeNewOne = Machine.all().map { it.toElement() }.size

                Machine.findById(machineId)?.delete()

                val machinesAfterAddingNewOne = Machine.all().map { it.toElement() }.size
                return@transaction machinesAfterAddingNewOne != machinesBeforeNewOne
            }
        } catch (e: ExposedSQLException) {
            println("Can not remove machine id: $machineId")
            return false
        }
    }

    fun enable(machineId: Int) = changeEnabled(machineId, status = true)

    fun disable(machineId: Int) = changeEnabled(machineId, status = false)

    private fun changeEnabled(machineId: Int, status: Boolean): Boolean {
        try {
            return transaction {
                val machineToEnable = Machine.findById(machineId)
                if (machineToEnable != null) {
                    machineToEnable.enabled = status
                    return@transaction true
                }
                return@transaction false
            }
        } catch (e: ExposedSQLException) {
            println("Can not change 'enabled' field: Machine(id: $machineId, status: $status)")
            return false
        }
    }

    fun protect(machineId: Int) = changeProtected(machineId, state = true)

    fun unprotect(machineId: Int) = changeProtected(machineId, state = false)

    private fun changeProtected(machineId: Int, state: Boolean): Boolean {
        try {
            return transaction {
                val machineToProtect = Machine.findById(machineId)
                if (machineToProtect != null) {
                    machineToProtect.protected = state
                    return@transaction true
                }
                return@transaction false
            }
        } catch (e: ExposedSQLException) {
            println("Can not change 'proteted' field: Machine(id: $machineId, state: $state)")
            return false
        }
    }


    fun protected(machineId: Int): Boolean? {
        val machine = get(machineId)
        return machine?.protected
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

    fun getAvailable(): List<Element> = allMachines().filter { it.start == null}

    private fun isAvailable(machineId: Int): Boolean {
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
                it.reservationStart = Instant.now().millis
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
