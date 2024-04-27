package woowacourse.movie.domain

import woowacourse.movie.R
import woowacourse.movie.domain.pricing.UniformPricingSystem
import woowacourse.movie.domain.reservation.Quantity
import woowacourse.movie.domain.reservation.TicketMachine
import woowacourse.movie.domain.screening.BasicScreeningScheduleSystem
import woowacourse.movie.domain.screening.Movie
import woowacourse.movie.domain.screening.Screening
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

object TestFixture {
    val MOCK_MOVIE =
        Movie(
            R.drawable.sorcerer_ston_poster,
            "제목",
            "설명",
            120,
        )

    val MOCK_MOVIES = mutableListOf(MOCK_MOVIE)

    val WEEKEND_TIMES =
        listOf(
            LocalTime.of(9, 0, 0),
            LocalTime.of(11, 0, 0),
            LocalTime.of(13, 0, 0),
            LocalTime.of(15, 0, 0),
            LocalTime.of(17, 0, 0),
            LocalTime.of(19, 0, 0),
            LocalTime.of(21, 0, 0),
            LocalTime.of(23, 0, 0),
        )

    val WEEKDAY_TIMES =
        listOf(
            LocalTime.of(10, 0, 0),
            LocalTime.of(12, 0, 0),
            LocalTime.of(14, 0, 0),
            LocalTime.of(16, 0, 0),
            LocalTime.of(18, 0, 0),
            LocalTime.of(20, 0, 0),
            LocalTime.of(22, 0, 0),
            LocalTime.of(0, 0, 0),
        )

//    fun reservationBuilder(
//        quantityValue: Int,
//        pricingSystem: PricingSystem,
//    ): TicketMachine {
//        val screening = screeningBuilder()
//        return TicketMachine(
//            0,
//            screening,
//            Quantity(quantityValue),
//            pricingSystem,
//        )
//    }

    val MOCK_SCREENING =
        Screening(
            0,
            MOCK_MOVIE,
            LocalDate.of(2024, 3, 1),
            LocalDate.of(2024, 4, 30),
            BasicScreeningScheduleSystem(),
        )

    val MOCK_TICKET =
        Ticket(
            0L,
            MOCK_MOVIE,
            LocalDateTime.of(
                LocalDate.of(2024, 4, 27),
                LocalTime.of(9, 0, 0),
            ),
            listOf("A1", "A2"),
            20000,
        )

    val MOCK_TICKET_MACHINE =
        TicketMachine(
            MOCK_SCREENING,
            Quantity(3),
            UniformPricingSystem(),
        )
}
