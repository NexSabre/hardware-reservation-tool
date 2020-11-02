package com.nexsabre.hardwarereservationtool

import com.nexsabre.hardwarereservationtool.configuration.Configuration
import com.nexsabre.hardwarereservationtool.models.Machines
import org.jetbrains.exposed.exceptions.ExposedSQLException
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class HardwareReservationToolApplication

fun main(args: Array<String>) {
    database()
    runApplication<HardwareReservationToolApplication>(*args)
}

fun database() {
    val dbConfig = Configuration().database()
    Database.connect(url = dbConfig.url, driver = dbConfig.driver)

    try {
        transaction {
            SchemaUtils.create(Machines)
        }
    } catch (e: ExposedSQLException) {
    }
}
