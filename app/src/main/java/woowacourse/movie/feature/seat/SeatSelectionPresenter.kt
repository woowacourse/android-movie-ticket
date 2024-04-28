package woowacourse.movie.feature.seat

import woowacourse.movie.data.MockScreeningRepository
import woowacourse.movie.data.MockTicketRepository
import woowacourse.movie.domain.screening.Screening
import woowacourse.movie.domain.seat.Seat
import woowacourse.movie.feature.main.ui.toUiModel
import woowacourse.movie.feature.reservation.ui.toUiModel
import java.time.LocalDateTime

class SeatSelectionPresenter(
    private val view: SeatSelectionContract.View,
    private val screeningId: Long,
    private val datePosition: Int,
    private val timePosition: Int,
) : SeatSelectionContract.Presenter {
    private lateinit var screening: Screening

    override fun fetchData() {
        screening = MockScreeningRepository.find(screeningId) ?: return
        val seats = MockScreeningRepository.getSeats(screeningId)
        view.initialize(screening.toUiModel(), seats.map { it.toUiModel() })
    }

    fun saveReservation(
        seatList: MutableList<String>,
        price: Long,
    ) {
        val dailySchedule = screening.schedule.dailySchedules[datePosition]
        val ticketId =
            MockTicketRepository.save(
                movie = screening.movie,
                schedule =
                    LocalDateTime.of(
                        dailySchedule.date,
                        dailySchedule.times[timePosition],
                    ),
                seats =
                    seatList.map {
                        Seat.of(
                            it[0],
                            it[1],
                        )
                    },
                price = price,
            )
        view.navigateToReservationCompleted(ticketId)
    }
}
