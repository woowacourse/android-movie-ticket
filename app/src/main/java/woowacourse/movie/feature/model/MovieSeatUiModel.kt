package woowacourse.movie.feature.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieSeatUiModel(
    val row: Int,
    val column: Int,
    val seatType: SeatTypeUiModel,
    val isSelected: Boolean = false,
) : Parcelable {
    fun toLabel(): String {
        val rowLabel = alphabets[row - 1].toString()
        val columnLabel = column.toString()

        return "$rowLabel$columnLabel"
    }

    companion object {
        private val alphabets = ('A'..'Z').toList()
    }
}
