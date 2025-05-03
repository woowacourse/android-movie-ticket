package woowacourse.movie.domain.seat

import java.io.Serializable

data class Seat(
    val row: Int,
    val col: Int,
) : Serializable
