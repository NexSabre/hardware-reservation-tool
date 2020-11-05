package com.nexsabre.hardwarereservationtool.models

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.jodatime.datetime

object Machines : IntIdTable() {
    val name = varchar("name", 50).index().uniqueIndex("name")
    val address = varchar("address", 20).uniqueIndex("address")
    val reservationStart = datetime("reservation_start").nullable()
    val reservationEnds = datetime("reservation_ends").nullable()
    val enabled = bool("enabled").default(true)
    val protected = bool("protected").default(false)
}

class Machine(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Machine>(Machines)

    var name by Machines.name
    var address by Machines.address
    var reservationStart by Machines.reservationStart
    var reservationEnds by Machines.reservationEnds
    var enabled by Machines.enabled
    var protected by Machines.protected
}

