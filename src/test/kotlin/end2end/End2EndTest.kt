package end2end

import com.nexsabre.hardwarereservationtool.server.models.Element
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import makeGetRequest
import makePostRequest
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test


fun countElementsOn(endpoint: String): Int = Json.decodeFromString<List<Element>>(makeGetRequest(endpoint = endpoint)).size


class End2EndTest {
    @Test
    fun `Make request to the api_v1_reservations`() {
        val response = makeGetRequest(endpoint = "/api/v1/reservations")
//        assertThat(response).isEqualTo("[]")
    }

    @Test
    fun `Add a new machine`() {
        val endpoint = "/api/v1/machines"
        val responseCountAllMachinesBefore = countElementsOn(endpoint)

        addNewMachineThruAPI()

        val responseCountAllMachinesAfter = countElementsOn(endpoint)
        Assertions.assertThat(responseCountAllMachinesAfter).isGreaterThan(responseCountAllMachinesBefore)
    }

    fun addNewMachineThruAPI() {
        val randomMachine = kotlin.random.Random(10).toString()
        val response = makePostRequest(endpoint = "/api/v1/machines", payload = mapOf(
                "name" to "Example machine $randomMachine",
                "address" to "1.1.1.$randomMachine"))
        print(response)
    }
}
