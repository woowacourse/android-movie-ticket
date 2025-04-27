package woowacourse.movie.domain.model.seat

import java.io.Serializable

data class SeatPosition(
    val x: Int,
    val y: Int
) : Serializable