package end2end

import makeRequest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class End2EndTest {
    @Test
    fun `Make request to the api_v1_reservations`() {
        val response = makeRequest(endpoint = "/api/v1/reservations")
        assertThat(response).isEqualTo("[]")
    }

}