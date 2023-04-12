package woowacourse.movie.domain

import java.io.Serializable
import java.time.LocalDate

data class Ticket(
    val price: Int,
    val date: LocalDate,
    val movieTitle: String,
    val numberOfPeople: Int,
) : Serializable
