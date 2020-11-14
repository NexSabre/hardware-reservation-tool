import com.nexsabre.hardwarereservationtool.server.controllers.ReservationMachine
import com.nexsabre.hardwarereservationtool.server.models.Element
import com.nexsabre.hardwarereservationtool.server.models.Machine
import com.nexsabre.hardwarereservationtool.server.models.Machines
import com.nexsabre.hardwarereservationtool.server.models.toElement
import org.jetbrains.exposed.exceptions.ExposedSQLException
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.Instant
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
open class BaseTest {
    @BeforeEach
    fun createDatabaseSchema() {
        Database.connect("jdbc:sqlite:sqlite.test.db", driver = "org.sqlite.JDBC")

        transaction {
            SchemaUtils.create(Machines)
        }
    }

    @AfterEach
    fun clean() {
        deleteAllExampleMachines()
    }

    protected fun addExampleMachine() {
        ReservationMachine().create("Example Machine", "0.0.0.0", null, null)
    }

    protected fun addFewExampleMachines() {
        val exampleMachines = listOf<Element>(
                Element(null, "Machine 1", "0.0.0.0"),
                Element(null, "Machine 2", "1.1.1.1")
        )
        ReservationMachine().create(exampleMachines)
    }

    protected fun allMachines(): List<Element> {
        return transaction {
            return@transaction Machine.all().map {
                it.toElement()
            }
        }
    }

    protected fun deleteAllExampleMachines(): Boolean {
        try {
            transaction {
                val machines = Machine.all()
                machines.forEach {
                    it.delete()
                }
            }
        } catch (e: ExposedSQLException) {
            println("Can not delete all example machines")
        }
        return allMachines().isEmpty()
    }

    protected fun deleteSpecificMachine(machineId: Int) {
        try {
            transaction {
                Machine.findById(machineId)?.delete()
            }
        } catch (e: ExposedSQLException) {
            println("Can not delete example machine with id: $machineId")
        }
    }

    protected fun getFirstAvailable(): Machine? {
        return transaction {
            return@transaction Machine.all().firstOrNull { it.reservationStart == null }
        }
    }

    protected fun getFirstReserved(): Machine? {
        return transaction {
            return@transaction Machine.all().firstOrNull { it.reservationStart != null }
        }
    }

    protected fun reserveFirstMachine() {
        val availableMachine = getFirstAvailable()
        transaction {
            availableMachine?.reservationStart = Instant.now().millis
        }
    }
}