package woowacourse.movie.domain

import java.io.Serializable
import java.time.LocalDate

class Movie(
    val title: String,
    val screeningDate: LocalDate,
    val runningTime: Minute,
    val poster: Poster,
    val movieDetail: MovieDetail
) : Serializable
