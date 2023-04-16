package woowacourse.movie.view

import java.io.Serializable

data class Reservation(
    val movie: MovieView,
    val detail: ReservationDetailView
) : Serializable {
    companion object {
        const val RESERVATION_EXTRA_NAME = "reservation"
    }
}
