package woowacourse.movie.domain.model.movie

import woowacourse.movie.domain.model.seat.Seat
import java.io.Serializable
import java.time.LocalDateTime

data class MovieTicket(
    val title: String,
    val screeningDateTime: LocalDateTime,
    val headCount: Int,
    val amount: Int = DEFAULT_AMOUNT,
    val seats: List<Seat> = emptyList()
) : Serializable {

    init {
        require(screeningDateTime.isAfter(LocalDateTime.now())) {
            DATETIME_ERROR
        }
    }

    companion object {
        private const val DEFAULT_AMOUNT = 0
        private const val DATETIME_ERROR = "[ERROR] 상영시간이 지난 영화입니다."
    }
}
