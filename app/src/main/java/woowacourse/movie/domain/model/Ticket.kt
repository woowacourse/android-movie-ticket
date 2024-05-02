package woowacourse.movie.domain.model

data class Ticket(
    val title: String,
    val movieDateTime: MovieDateTime,
    val seats: List<MovieSeat>,
    val count: Int,
    val price: Int,
)
