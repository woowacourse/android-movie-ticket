package woowacourse.movie.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Seats(val value: List<Seat>) : Parcelable {
    fun toStringSeats(): MutableList<String> {
        val string = mutableListOf<String>()
        string += value.map {
            it.convertString()
        }
        return string
    }
}
