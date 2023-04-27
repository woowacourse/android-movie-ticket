package woowacourse.movie.uimodel

import java.io.Serializable
import java.time.LocalDateTime

class ReservationOptionModel(
    val movieModel: MovieModel,
    val ticketCount: Int,
    val screeningDateTime: LocalDateTime
) : Serializable {

    companion object {
        const val RESERVATION_OPTION_INTENT_KEY = "reservation_option"
    }
}
