package woowacourse.movie.presentation.detail

import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.ReservationCount
import java.time.LocalDate
import java.time.LocalTime

data class DetailUiModel(
    val movie: Movie? = null,
    val selectedLocalDate: LocalDate? = null,
    val selectedLocalTime: LocalTime? = null,
    val localDates: List<LocalDate>? = null,
    val localTimes: List<LocalTime>? = null,
    val reservationCount: ReservationCount = ReservationCount(),
)
