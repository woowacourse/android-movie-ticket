package woowacourse.movie.domain.movie

import java.time.LocalDateTime

data class Screen(
    val id: Int,
    val movie: Movie,
    val screenDateTime: LocalDateTime,
)
