package woowacourse.movie.domain.model

import java.io.Serializable
import java.time.LocalDateTime

data class ReservedMovie(
    val movieId: Int,
    val screeningDateTime: LocalDateTime,
    val headCount: Int,
) : Serializable
