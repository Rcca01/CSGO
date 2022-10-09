package com.example.csgo.utils

import java.lang.Exception
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class Utils {

    fun getDayFromDateString(stringDate: String): String {
        val calendar = Calendar.getInstance()
        calendar.firstDayOfWeek
        var day = ""
        try {
            val date = LocalDateTime.parse(stringDate.replace("Z", ""))
            day = date.format(DateTimeFormatter.ofPattern("dd")) + "." + date.format(DateTimeFormatter.ofPattern("MM")) +
                    " " + date.format(DateTimeFormatter.ofPattern("H:mm"))
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return day
    }
}