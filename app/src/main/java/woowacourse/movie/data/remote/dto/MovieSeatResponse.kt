package woowacourse.movie.data.remote.dto

data class MovieSeatResponse(
    val id: Long,
    val movieSeatBoardId: Long,
    val number: Int,
    val tier: TierResponse,
)
