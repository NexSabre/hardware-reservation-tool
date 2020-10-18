package com.nexsabre.hardwarereservationtool

import BaseTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HardwareReservationToolApplicationTests(@Autowired val restTemplate: TestRestTemplate) : BaseTest() {

    @Test
    fun `Assert it return a example machine`() {
        addExampleMachine()
        val firstMachine = allMachines()[0]

        val entity = restTemplate.getForEntity<String>("/api/v1/reserve/${firstMachine.id}")
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
    }

    @Test
    fun `Assert it do not return a example machine`() {
        deleteAllExampleMachines()

        val entity = restTemplate.getForEntity<String>("/api/v1/reserve/1")
        assertThat(entity.statusCode).isEqualTo(HttpStatus.NOT_FOUND)
    }
}
