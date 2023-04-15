package woowacourse.movie.model

import com.example.domain.model.Count
import java.io.Serializable
import java.time.LocalDateTime

data class ReservationInfo private constructor(
    private val movieRes: MovieRes,
    private val dateTime: LocalDateTime,
    private val count: Int
) : Serializable {

    fun getInfo(): Triple<MovieRes, LocalDateTime, Count> {
        return Triple(movieRes, dateTime, Count(count))
    }

    companion object {
        private const val COUNT_ERROR_MESSAGE = "[ERROR] 예약 인원 수는 1명 이상이여야 합니다."
        private const val MIN_COUNT_RANGE = 1

        fun from(
            movieRes: MovieRes,
            dateTime: LocalDateTime,
            count: Count
        ): ReservationInfo {
            return ReservationInfo(movieRes, dateTime, count.value)
        }
    }
}
