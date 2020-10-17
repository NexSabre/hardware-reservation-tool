package com.nexsabre.hardwarereservationtool

import com.nexsabre.hardwarereservationtool.models.Machine
import com.nexsabre.hardwarereservationtool.models.Machines
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.springframework.boot.test.autoconfigure.jdbc.TestDatabaseAutoConfiguration
import org.springframework.http.HttpStatus
import org.springframework.test.context.event.annotation.BeforeTestClass
import javax.xml.crypto.Data

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HardwareReservationToolApplicationTests(@Autowired val restTemplate: TestRestTemplate) {

	@BeforeEach
	fun setUp() {
		Database.connect("jdbc:sqlite:sqlite.test.db", driver = "org.sqlite.JDBC")
	}

	@Test
	fun `Assert it works`() {
		transaction {
			SchemaUtils.create(Machines)

			Machine.new {
				name = "Example machine"
				address = "0.0.0.0"
			}
		}

		val entity = restTemplate.getForEntity<String>("/api/v1/reserve/1")
		assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
	}
}
