package woowacourse.movie.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class SeatColumn(val value: Int) : Parcelable {
    override fun toString(): String = "$value"

    companion object {
        fun make(size: Int): List<SeatColumn> = (1..size).map(::SeatColumn)
    }
}
