package com.woowacourse.movie.domain

import java.time.LocalDate

typealias MovieDomain = Movie

data class Movie(
    val title: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val runningTime: Int,
    val introduce: String,
    val thumbnail: Int,
)
