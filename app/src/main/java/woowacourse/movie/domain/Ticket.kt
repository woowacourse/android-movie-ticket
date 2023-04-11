package woowacourse.movie.domain

import java.util.*

data class Ticket(
    val price: Int,
    val date: Date,
    val movie: Movie
)
