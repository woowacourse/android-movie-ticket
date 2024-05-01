package woowacourse.movie.domain

import java.io.Serializable

data class MovieSeat(
    val id: Long,
    val movieSeatBoardId: Long,
    val number: Int,
    val tier: Tier,
) : Serializable
