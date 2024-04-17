package woowacourse.movie

import java.io.Serializable

data class Ticket(
    val title: String,
    val screeningDate: String,
    val price: Int,
    val numberOfPeople: Int,
) : Serializable
