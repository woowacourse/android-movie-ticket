package woowacourse.movie.model

data class ScreeningMovie(
    val id: Long,
    val movie: Movie,
    val ticketPrice: Int,
    val screenDateTimes: List<ScreenDateTime>,
)
