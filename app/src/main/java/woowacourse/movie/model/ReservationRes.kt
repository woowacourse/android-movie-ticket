package woowacourse.movie.model

import com.example.domain.model.Count
import java.io.Serializable
import java.time.LocalDateTime

data class ReservationRes private constructor(
    private val movieRes: MovieRes,
    private val dateTime: LocalDateTime,
    private val count: Int
) : Serializable {

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
