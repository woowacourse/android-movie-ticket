package woowacourse.movie.model

import android.os.Parcelable
import com.example.domain.model.Count
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class ReservationState private constructor(
    private val movieRes: MovieState,
    private val dateTime: LocalDateTime,
    private val count: Int
) : Parcelable {

    fun getInfo(): Triple<MovieState, LocalDateTime, Count> {
        return Triple(movieRes, dateTime, Count(count))
    }

    companion object {
        fun from(
            movieRes: MovieState,
            dateTime: LocalDateTime,
            count: Count
        ): ReservationState {
            return ReservationState(movieRes, dateTime, count.value)
        }
    }
}
