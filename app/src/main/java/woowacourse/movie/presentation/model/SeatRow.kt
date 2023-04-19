package woowacourse.movie.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class SeatRow(val value: Char) : Parcelable, Comparable<SeatRow> {
    override fun compareTo(other: SeatRow): Int = value.compareTo(other.value)

    companion object {
        fun make(size: Int): List<SeatRow> = (1..size).map { SeatRow((it + 64).toChar()) }
    }
}
