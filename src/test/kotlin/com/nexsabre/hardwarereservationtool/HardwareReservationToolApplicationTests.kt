package com.nexsabre.hardwarereservationtool

import com.nexsabre.hardwarereservationtool.models.Machine
import com.nexsabre.hardwarereservationtool.models.Machines
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HardwareReservationToolApplicationTests(@Autowired val restTemplate: TestRestTemplate) {

	@BeforeEach
	fun setUp() {
		Database.connect("jdbc:sqlite:sqlite.test.db", driver = "org.sqlite.JDBC")
	}

	@Test
	fun `Assert it works`() {
		addExampleMachine()
		val entity = restTemplate.getForEntity<String>("/api/v1/reserve/1")
		assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
	}

	private fun addExampleMachine() {
		try {
			transaction {
				SchemaUtils.create(Machines)

				Machine.new {
					name = "Example machine"
					address = "0.0.0.0"
				}
			}
		} catch (e: org.jetbrains.exposed.exceptions.ExposedSQLException) {}
	}
}
