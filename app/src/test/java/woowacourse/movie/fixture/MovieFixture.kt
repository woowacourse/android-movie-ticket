package woowacourse.movie.fixture

import woowacourse.movie.R
import woowacourse.movie.domain.Date
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.ReservationInfo
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

val MOVIE = Movie(
    R.drawable.harry_potter_and_the_philosopher_stone,
    "해리 포터와 마법사의 돌",
    Date(LocalDate.of(2025, 9, 29), LocalDate.of(2025, 9, 30)),
    152,
)

val SELECTED_DATES = listOf(LocalDate.of(2025, 9, 29), LocalDate.of(2025, 9, 30))
val SELECTED_TIMES = listOf(LocalTime.of(10, 0), LocalTime.of(12, 0), LocalTime.of(14, 0), LocalTime.of(16, 0), LocalTime.of(18, 0), LocalTime.of(20, 0), LocalTime.of(22, 0))

val RESERVATION_INFO = ReservationInfo("너와 나", LocalDateTime.of(2025,4,30,14,0), 2)
