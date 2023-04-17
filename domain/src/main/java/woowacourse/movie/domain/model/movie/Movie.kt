package woowacourse.movie.domain.model.movie

import java.time.LocalDate

typealias DomainMovie = Movie

data class Movie(
    val title: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val runningTime: Int,
    val introduce: String,
)
