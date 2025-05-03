package woowacourse.movie.domain.movies

import java.io.Serializable
import java.time.LocalDateTime

data class MovieToReserve(
    val movieId: Int,
    val screeningDateTime: LocalDateTime,
    val headCount: Int,
) : Serializable
