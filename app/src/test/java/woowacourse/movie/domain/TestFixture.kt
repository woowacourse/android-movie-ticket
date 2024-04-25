package woowacourse.movie.domain

import woowacourse.movie.R
import woowacourse.movie.domain.pricing.PricingSystem
import woowacourse.movie.domain.reservation.Quantity
import woowacourse.movie.domain.reservation.Reservation
import woowacourse.movie.domain.screening.Movie
import woowacourse.movie.domain.screening.Schedule
import woowacourse.movie.domain.screening.Screening
import java.time.LocalDate

object TestFixture {
    val DUMMY_MOVIE =
        Movie(0, R.drawable.poster, "제목", "설명", "2024.3.1", 120)

    fun reservationBuilder(
        quantityValue: Int,
        pricingSystem: PricingSystem,
    ): Reservation {
        val screening = screeningBuilder()
        return Reservation(
            0,
            screening,
            Quantity(quantityValue),
            pricingSystem,
        )
    }

    fun screeningBuilder() =
        Screening(
            DUMMY_MOVIE,
            Schedule(LocalDate.of(2024, 3, 1)),
        )
}
