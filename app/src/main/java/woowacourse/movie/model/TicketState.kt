package woowacourse.movie.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class TicketState(
    val count: CountState,
    val movieData: MovieDataState,
    val screeningDateTime: ScreeningDateTimeState,
    var price: Int = 0,
    val seatSelection: MutableSet<SeatState> = mutableSetOf()
) : Parcelable
