package woowacourse.movie.model

import woowacourse.movie.R
import woowacourse.movie.model.pricing.PricingSystem
import woowacourse.movie.model.screening.Schedule
import woowacourse.movie.model.screening.Screening
import java.time.LocalDate

object TestFixture {
    private val DUMMY_MOVIE =
        Movie(R.drawable.poster, "제목", "설명", "2024.3.1", 120)

    fun reservationBuilder(
        quantityValue: Int,
        pricingSystem: PricingSystem,
    ): Reservation {
        val screening = screeningBuilder(quantityValue)
        return Reservation(screening, pricingSystem)
    }

    fun screeningBuilder(quantityValue: Int) =
        Screening(
            DUMMY_MOVIE,
            Schedule(LocalDate.of(2024, 3, 1)),
            Quantity(quantityValue),
        )
}
