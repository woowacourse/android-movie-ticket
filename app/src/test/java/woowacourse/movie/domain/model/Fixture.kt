package woowacourse.movie.domain.model

import woowacourse.movie.R
import woowacourse.movie.model.Movie
import woowacourse.movie.model.MovieTicket
import woowacourse.movie.model.ReservationInfo
import woowacourse.movie.model.Seats
import java.time.LocalDate

val dummyMovie =
    Movie(
        "라라랜드",
        R.drawable.lalaland,
        LocalDate.of(2025, 4, 1),
        LocalDate.of(2025, 4, 30),
        120,
    )

val dummyTicket =
    MovieTicket(
        "라라랜드",
        LocalDate.of(2025, 4, 1),
        "14:00",
        3,
    )

val dummyReservationInfo =
    ReservationInfo(
        "라라랜드",
        LocalDate.of(2025, 4, 1),
        "14:00",
        Seats.create(),
        20000,
    )
