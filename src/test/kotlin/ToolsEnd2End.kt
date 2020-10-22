import khttp.post
import khttp.responses.Response
import java.net.HttpURLConnection
import java.net.URL

fun makeGetRequest(baseUrl: String = "http://localhost:8080", endpoint: String, method: String = "GET"): String {
    val url = URL(baseUrl + endpoint)

    with(url.openConnection() as HttpURLConnection) {
        requestMethod = method
        return inputStream.bufferedReader().readText()
    }
}

fun makePostRequest(baseUrl: String = "http://localhost:8080", endpoint: String, payload: Any?): Response =
        post(baseUrl + endpoint, data = payload)