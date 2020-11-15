package com.nexsabre.hardwarereservationtool.cmd.menu

import com.github.ajalt.clikt.core.CliktCommand
import com.nexsabre.hardwarereservationtool.cmd.helpers.prettyPrint
import com.nexsabre.hardwarereservationtool.cmd.helpers.reservations.getAllReservations


class Reservations : CliktCommand() {
    override fun run() {
        getAllReservations().prettyPrint()
    }
}