package com.nexsabre.hardwarereservationtool

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class HardwareReservationToolApplication

fun main(args: Array<String>) {
	runApplication<HardwareReservationToolApplication>(*args)
}
