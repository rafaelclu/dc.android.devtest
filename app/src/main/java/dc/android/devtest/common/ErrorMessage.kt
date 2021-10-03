package dc.android.devtest.common

import android.content.res.Resources
import dc.android.devtest.R
import dc.android.devtest.data.exception.JsonReadException
import dc.android.devtest.data.exception.ServerConnectionException
import retrofit2.HttpException
import java.net.SocketTimeoutException

fun resolveError(e: Exception, resources: Resources): String = when (e) {
    is HttpException -> {
        when (e.code()) {
            500 -> resources.getString(R.string.internal_server_error)
            else -> resources.getString(R.string.unknown_error)
        }
    }

    is SocketTimeoutException -> resources.getString(R.string.socket_timeout_error)

    is JsonReadException -> resources.getString(R.string.io_error)

    is ServerConnectionException -> resources.getString(R.string.server_connection_error)

    else -> resources.getString(R.string.unknown_error)
}