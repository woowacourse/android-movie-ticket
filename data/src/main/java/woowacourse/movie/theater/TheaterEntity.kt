package woowacourse.movie.theater

data class TheaterEntity(
    val id: Long,
    val rowSize: Int,
    val columnSize: Int,
    val sRankRange: List<IntRange>,
    val aRankRange: List<IntRange>,
    val bRankRange: List<IntRange>,
)
