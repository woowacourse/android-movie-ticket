package woowacourse.movie.view.data

import java.io.Serializable

data class SeatsViewData(
    val seats: List<SeatViewData>
) : Serializable {
    companion object {
        const val SEATS_EXTRA_NAME = "seats"
    }
}
