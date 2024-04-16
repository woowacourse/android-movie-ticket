package woowacourse.movie

import java.io.Serializable

data class Ticket(
    val movie: Movie,
    val quantity: Int,
) : Serializable
