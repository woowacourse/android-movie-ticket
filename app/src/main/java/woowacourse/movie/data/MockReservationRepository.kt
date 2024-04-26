package woowacourse.movie.data

import woowacourse.movie.R
import woowacourse.movie.domain.pricing.UniformPricingSystem
import woowacourse.movie.domain.reservation.Quantity
import woowacourse.movie.domain.reservation.Reservation
import woowacourse.movie.domain.screening.DailySchedule
import woowacourse.movie.domain.screening.Movie
import woowacourse.movie.domain.screening.Screening
import woowacourse.movie.domain.screening.ScreeningDate
import woowacourse.movie.domain.screening.ScreeningSchedule
import java.time.LocalDate

object MockReservationRepository : ReservationRepository {
    private var id: Long = 1L
    private val reservations: MutableList<Reservation> =
        mutableListOf(
            Reservation(
                0,
                Screening(
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
                    ),
                    ScreeningSchedule(listOf(DailySchedule(LocalDate.of(2024, 3, 1)))),
                ),
                Quantity(3),
                UniformPricingSystem(),
            ),
        )

    override fun findAll(): List<Reservation> = reservations.toList()

    override fun find(id: Long): Reservation? = reservations.find { it.id == id }

    override fun save(
        screening: Screening,
        quantity: Quantity,
    ): Long {
        val item =
            Reservation(
                id,
                screening.copy(),
                quantity.copy(),
            ).copy()
        reservations.add(item)
        return id++
    }
}
