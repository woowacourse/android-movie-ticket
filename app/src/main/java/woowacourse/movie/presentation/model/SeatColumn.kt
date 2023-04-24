package woowacourse.movie.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class SeatColumn(val value: Int) : Parcelable, Comparable<SeatColumn> {
    override fun compareTo(other: SeatColumn): Int = value.compareTo(other.value)

    override fun toString(): String = "$value"

    companion object {
        fun make(size: Int): List<SeatColumn> = (1..size).map(::SeatColumn)
    }
}
