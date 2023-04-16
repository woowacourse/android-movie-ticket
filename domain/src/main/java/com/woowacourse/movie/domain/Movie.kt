package com.woowacourse.movie.domain

import java.io.Serializable
import java.time.LocalDate
import java.time.format.DateTimeFormatter

typealias MovieDomain = Movie

data class Movie(
    val title: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val runningTime: Int,
    val introduce: String,
    val thumbnail: Int,
) : Serializable {
    val LocalDate.formattedDate: String
        get() = this.format(DateTimeFormatter.ofPattern(MOVIE_DATE_PATTERN))

    companion object {
        private const val MOVIE_DATE_PATTERN = "yyyy.MM.dd"
    }
}
