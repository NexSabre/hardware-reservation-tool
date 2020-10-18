package com.nexsabre.hardwarereservationtool

import com.nexsabre.hardwarereservationtool.models.Machines
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
	Database.connect("jdbc:sqlite:sqlite.test.db", driver = "org.sqlite.JDBC")

	transaction {
		SchemaUtils.create(Machines)
	}
}