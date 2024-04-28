package woowacourse.movie.model.ticketing

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate
import java.time.LocalTime

@Parcelize
class BookingDateTime(
    val date: LocalDate,
    val time: LocalTime,
) : Parcelable {
    companion object {
        fun of(
            date: String,
            time: String,
        ): BookingDateTime {
            return BookingDateTime(
                LocalDate.parse(date),
                LocalTime.parse(time),
            )
        }
    }
}
