package com.nexsabre.hardwarereservationtool.api.v1

import BaseTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class ReserveTest(@Autowired val restTemplate: TestRestTemplate): BaseTest() {

    @BeforeEach
    fun createOneMachine() {
        addExampleMachine()
    }

    @AfterEach
    fun cleanAfterEach() {
        deleteAllExampleMachines()
    }

    @Test
    fun reserveMachine() {
        val availableForReservation = getFirstAvailable()
        if (availableForReservation == null)
            fail<String>("No entries in database")

        val responseEntity = restTemplate.getForEntity<String>("/api/v1/reserve/${availableForReservation?.id}")

        assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.OK)
    }

    @Test
    fun releaseMachine() {
        reserveFirstMachine()
        val reservedMachineForRelease = getFirstReserved()
        if (reservedMachineForRelease == null)
            fail<String>("No entries in database")

        val responseEntity = restTemplate.getForEntity<String>("/api/v1/reserve/${reservedMachineForRelease?.id}/release")

        assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.OK)
    }
}