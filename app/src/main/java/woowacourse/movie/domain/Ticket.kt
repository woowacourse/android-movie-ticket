package woowacourse.movie.domain

import java.io.Serializable
import java.util.*

data class Ticket(
    val price: Int,
    val date: Date,
    val movieTitle: String,
    val numberOfPeople: Int,
) : Serializable
