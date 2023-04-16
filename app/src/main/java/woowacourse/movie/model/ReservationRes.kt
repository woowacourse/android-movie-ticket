package woowacourse.movie.model

import android.os.Parcelable
import com.example.domain.model.Count
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class ReservationRes private constructor(
    private val movieRes: MovieRes,
    private val dateTime: LocalDateTime,
    private val count: Int
) : Parcelable {

    fun getInfo(): Triple<MovieRes, LocalDateTime, Count> {
        return Triple(movieRes, dateTime, Count(count))
    }

    companion object {
        fun from(
            movieRes: MovieRes,
            dateTime: LocalDateTime,
            count: Count
        ): ReservationRes {
            return ReservationRes(movieRes, dateTime, count.value)
        }
    }
}
