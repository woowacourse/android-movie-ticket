package woowacourse.movie.domain

import woowacourse.movie.R
import woowacourse.movie.domain.pricing.PricingSystem
import woowacourse.movie.domain.pricing.UniformPricingSystem
import woowacourse.movie.domain.reservation.Quantity
import woowacourse.movie.domain.reservation.Reservation
import woowacourse.movie.domain.screening.DailySchedule
import woowacourse.movie.domain.screening.Movie
import woowacourse.movie.domain.screening.Screening
import woowacourse.movie.domain.screening.ScreeningDate
import woowacourse.movie.domain.screening.ScreeningSchedule
import java.time.LocalDate

object TestFixture {
    val DUMMY_MOVIE =
        Movie(
            0,
            R.drawable.poster,
            "제목",
            "설명",
            ScreeningDate(
                LocalDate.of(2024, 3, 1),
                LocalDate.of(2024, 4, 30),
            ),
            120
        )

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
            ScreeningSchedule(
                listOf(
                    DailySchedule(
                        LocalDate.of(
                            2024,
                            3,
                            1
                        )
                    )
                )
            )
        )

    val MOCK_RESERVATION =
        Reservation(
            0,
            Screening(
                Movie(
                    0, R.drawable.poster, "제목", "설명",
                    ScreeningDate(
                        LocalDate.of(2024, 3, 1),
                        LocalDate.of(2024, 4, 30),
                    ), 120
                ),
                ScreeningSchedule(
                    listOf(
                        DailySchedule(
                            LocalDate.of(
                                2024,
                                3,
                                1
                            )
                        )
                    )
                ),
            ),
            Quantity(3),
            UniformPricingSystem(),
        )
}
