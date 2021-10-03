package dc.android.devtest.common.extension

import java.text.SimpleDateFormat
import java.util.*

/**
 * Converts a date String to a Calendar object.
 *
 * @throws [IllegalArgumentException] if [String] is not a valid date
 */
fun String.toCalendar(): Calendar {
    val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    val date: Date? = simpleDateFormat.parse(this)
    val calendar: Calendar = Calendar.getInstance()

    require(date != null) { "Unable to convert string to Date" }
    calendar.time = date

    return calendar
}

/**
 * Converts a Date to a String giving a pattern.
 *
 */
fun Date.formatToLocalizedDate(pattern: String): String =
    SimpleDateFormat(pattern, Locale.getDefault()).format(this)