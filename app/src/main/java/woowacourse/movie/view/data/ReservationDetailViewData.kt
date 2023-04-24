package woowacourse.movie.view.data

import java.io.Serializable
import java.time.LocalDateTime

data class ReservationDetailViewData(
    val date: LocalDateTime,
    val peopleCount: Int
) : Serializable {
    companion object {
        const val RESERVATION_DETAIL_EXTRA_NAME = "reservation_detail"
    }
}
