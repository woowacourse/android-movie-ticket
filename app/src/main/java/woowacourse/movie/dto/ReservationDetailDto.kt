package woowacourse.movie.dto

import java.io.Serializable
import java.time.LocalDateTime

data class ReservationDetailDto(
    val date: LocalDateTime,
    val peopleCount: Int,
    val price: Int
) : Dto, Serializable
