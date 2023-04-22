package woowacourse.movie.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@JvmInline
@Parcelize
value class Seats(val value: List<Seat>) : Parcelable {
    fun toStringSeats(): MutableList<String> {
        val string = mutableListOf<String>()
        string += value.map {
            it.convertString()
        }
        return string
    }
}
