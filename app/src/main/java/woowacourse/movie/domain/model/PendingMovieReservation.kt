package woowacourse.movie.domain.model

data class PendingMovieReservation(
    val movieId: Int,
    val title: String,
    val movieDateTime: MovieDateTime,
    val count: Int,
)
