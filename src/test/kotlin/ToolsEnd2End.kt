import java.net.HttpURLConnection
import java.net.URL

fun makeRequest(baseUrl: String = "http://localhost:8080", endpoint: String, method: String = "GET"): String {
    val url = URL("http://localhost:8080/api/v1/reservations")

    with(url.openConnection() as HttpURLConnection) {
        requestMethod = "GET"
        return inputStream.bufferedReader().readText()
    }
}