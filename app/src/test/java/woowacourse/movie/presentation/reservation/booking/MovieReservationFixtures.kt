package woowacourse.movie.presentation.reservation.booking

import woowacourse.movie.model.HeadCount
import woowacourse.movie.model.MovieReservation
import woowacourse.movie.model.board.Seats
import woowacourse.movie.presentation.reservation.booking.model.MovieReservationUiState
import woowacourse.movie.presentation.reservation.booking.model.ScreenDateTimeUiModel
import woowacourse.movie.presentation.screening.ScreeningMovieUiModel
import woowacourse.movie.presentation.screening.stubMovie
import java.time.LocalDateTime
import kotlin.time.Duration.Companion.minutes

fun stubMovieReservation(): MovieReservation {
    return MovieReservation(
        id = 1L,
        movie = stubMovie(),
        screenDateTime = LocalDateTime.now(),
        headCount = HeadCount(2),
        cancelDeadLine = 15.minutes,
        seats = Seats(),
    )
}

fun stubMovieReservationUiState(): MovieReservationUiState {
    return MovieReservationUiState(
        movie = ScreeningMovieUiModel(),
        id = 1L,
        count = 2,
        screenDateTimes = emptyList(),
        selectedDate = ScreenDateTimeUiModel(),
        selectedTime = "",
    )
}
