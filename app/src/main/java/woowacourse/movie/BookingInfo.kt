package woowacourse.movie

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate
import java.time.LocalTime

@Parcelize
data class BookingInfo(
    val movie: Movie = Movie(),
    val date: LocalDate = LocalDate.now(),
    val movieTime: MovieTime = MovieTime(LocalTime.now()),
    val count: Int = 0,
    val eachPrice: Int = DEFAULT_TICKET_PRICE,
) : Parcelable {
    val totalPrice: Int
        get() = eachPrice * count

    companion object {
        private const val DEFAULT_TICKET_PRICE = 13_000
    }
}
