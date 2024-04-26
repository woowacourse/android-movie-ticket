package woowacourse.movie.domain.model

data class Ticket(
    val title: String,
    val movieDate: MovieDate,
    val seats: List<MovieSeat>,
    val count: Int,
    val price: Int,
)
