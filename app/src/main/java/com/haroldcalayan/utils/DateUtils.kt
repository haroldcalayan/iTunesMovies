package com.haroldcalayan.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    private const val DATE_PATTERN = "yyyy-MM-dd"

    fun today() : String {
        val current = Date()
        val formatter = SimpleDateFormat(DATE_PATTERN)
        val formatted = formatter.format(current)
        return formatted
    }
}