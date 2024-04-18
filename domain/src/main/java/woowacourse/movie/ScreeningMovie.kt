package woowacourse.movie

data class ScreeningMovie(
    val id: Long,
    val movie: Movie,
    val price: Price,
    val screenDateTimes: List<ScreenDateTime>,
)


