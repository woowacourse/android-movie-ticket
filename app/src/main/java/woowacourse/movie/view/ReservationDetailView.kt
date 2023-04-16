package woowacourse.movie.view

import java.io.Serializable
import java.time.LocalDateTime

data class ReservationDetailView(
    val date: LocalDateTime,
    val peopleCount: Int,
    val price: Int
) : Serializable
