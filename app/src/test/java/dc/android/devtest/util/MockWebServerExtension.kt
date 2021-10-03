package dc.android.devtest.util

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import java.io.File

fun MockWebServer.networkResponseWithFileContent(fileName: String, responseCode: Int) {
    val uri = javaClass.classLoader!!.getResource(fileName)
    val file = File(uri.path)
    val json = String(file.readBytes())

    this.enqueue(
        MockResponse()
            .setResponseCode(responseCode)
            .setBody(json)
    )

}