package com.winsontan520.starwars.util

import java.text.SimpleDateFormat
import java.util.*

object DateUtil {

    const val DATE_FORMAT_FROM_SERVER = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    const val DATE_FORMAT_LAST_UPDATE = "dd/MM/yyyy - h:mm a"

    fun getDateFromString(dateString: String, format: String): Date {
        val sdf = SimpleDateFormat(format, Locale.US)
        return sdf.parse(dateString)
    }

    fun getStringFromDate(date: Date, format: String): String {
        return SimpleDateFormat(format, Locale.US).format(date)
    }

    fun getCurrentDateString(): String {
        val c = Calendar.getInstance().time
        val df = SimpleDateFormat(DATE_FORMAT_FROM_SERVER, Locale.US)
        return df.format(c)
    }
}