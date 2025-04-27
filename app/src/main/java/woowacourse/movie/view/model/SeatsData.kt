package woowacourse.movie.view.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SeatsData(
    val seatsLength: Int,
    val totalSeatsPrice: Int,
    val seatsCodes: List<String>,
) : Parcelable {
    companion object {
        fun getEmptySeatsData() = SeatsData(0, 0, listOf())
    }
}
