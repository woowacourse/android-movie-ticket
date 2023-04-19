package com.example.domain.model.formatter

import java.time.LocalTime
import java.time.format.DateTimeFormatter

object TimeFormatter : Formatter<LocalTime>() {
    override val formatString: String = "HH:mm"

    override fun format(data: LocalTime): String {
        val timeFormatter = DateTimeFormatter.ofPattern(formatString)
        return timeFormatter.format(data)
    }
}
