package woowacourse.movie.model

import android.os.Parcelable
import com.example.domain.model.Count
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class ReservationState private constructor(
    val movieState: MovieState,
    val dateTime: LocalDateTime,
    val countState: CountState
) : Parcelable {

    companion object {
        fun from(
            movieRes: MovieState,
            dateTime: LocalDateTime,
            count: Count
        ): ReservationState {
            return ReservationState(movieRes, dateTime, CountState.from(count))
        }
    }
}
