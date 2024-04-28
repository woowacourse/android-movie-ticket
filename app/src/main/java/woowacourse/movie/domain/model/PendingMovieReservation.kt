package woowacourse.movie.domain.model

data class PendingMovieReservation(
    val title: String,
    val movieDate: MovieDate,
    val count: Int,
)
