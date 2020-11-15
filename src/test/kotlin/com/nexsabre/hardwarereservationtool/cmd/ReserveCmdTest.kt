package com.nexsabre.hardwarereservationtool.cmd

import com.nexsabre.hardwarereservationtool.cmd.helpers.getAllReservations
import com.nexsabre.hardwarereservationtool.server.models.Element
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.system.exitProcess
import com.nexsabre.hardwarereservationtool.cmd.main as Application

internal class ReserveCmdTest {
    private lateinit var machineForTest: Element

    @BeforeEach
    fun findBestMachine() {
        val allReservations = getAllReservations()
        if (allReservations!!.isEmpty()) {
            exitProcess(0)
        }
        machineForTest = allReservations.first {
            it.enabled && !it.protected
        }
    }

    @Test
    fun `Reserve first available machine`() {
        Application(arrayOf("reserve", "-i", machineForTest.id.toString()))

        val allReservationAfterOperation = getAllReservations()
        val reserved = allReservationAfterOperation?.first {
            it.id == machineForTest.id
        }
        assertNotNull(reserved)
        assertNotNull(reserved?.start)
    }
}