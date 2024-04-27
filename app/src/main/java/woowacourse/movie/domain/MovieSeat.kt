package woowacourse.movie.domain

data class MovieSeat(
    val id: Long,
    val movieSeatBoardId: Long,
    val number: Int,
    val tier: Tier,
)
