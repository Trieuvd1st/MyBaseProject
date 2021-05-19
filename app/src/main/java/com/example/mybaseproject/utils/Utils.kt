package com.example.mybaseproject.utils

import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class Utils {
    companion object {
        fun formattedDate(dateUTC: String?): String? {
            val targetFormat: DateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm")
            var sd: Date? = null
            try {
                sd = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(dateUTC)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return if (sd != null) targetFormat.format(sd) else " "
        }
    }
}