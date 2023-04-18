package woowacourse.movie.view

import java.io.Serializable

data class ReservationData(
    val movie: MovieViewData,
    val detail: ReservationDetailViewData
) : Serializable {
    companion object {
        const val RESERVATION_EXTRA_NAME = "reservation"
    }
}
