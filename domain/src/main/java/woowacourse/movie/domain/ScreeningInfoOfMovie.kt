package woowacourse.movie.domain

import java.time.LocalDateTime

data class ScreeningInfoOfMovie(val screeningDateTime: LocalDateTime, val movieHouse: MovieHouse)
