package woowacourse.movie.domain.model

data class Ticket(
    val title: String,
    val screeningDate: String,
    val count: Int,
    val price: Int,
)

