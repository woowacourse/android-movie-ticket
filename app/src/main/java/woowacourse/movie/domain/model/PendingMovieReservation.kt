package woowacourse.movie.domain.model

data class PendingMovieReservation(
    val title: String,
    val movieDateTime: MovieDateTime,
    val count: Int,
)
