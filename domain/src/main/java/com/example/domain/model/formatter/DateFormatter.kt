package com.example.domain.model.formatter

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object DateFormatter : Formatter<LocalDate>() {
    override val formatString: String = "YYYY.M.d"

    override fun format(data: LocalDate): String {
        val dateFormatter = DateTimeFormatter.ofPattern(formatString)
        return dateFormatter.format(data)
    }
}
