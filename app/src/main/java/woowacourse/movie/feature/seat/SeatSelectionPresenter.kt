package woowacourse.movie.feature.seat

import woowacourse.movie.data.MockScreeningRepository
import woowacourse.movie.data.MockTicketRepository
import woowacourse.movie.domain.screening.Screening
import woowacourse.movie.domain.seat.Seat
import woowacourse.movie.feature.main.ui.toUiModel
import woowacourse.movie.feature.reservation.ui.toUiModel
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class SeatSelectionPresenter(
    private val view: SeatSelectionContract.View,
    private val screeningId: Long,
    private val datePosition: Int,
    private val timePosition: Int,
) : SeatSelectionContract.Presenter {
    lateinit var screening: Screening
    lateinit var date: LocalDate
    lateinit var time: LocalTime

    override fun fetchData() {
        screening = MockScreeningRepository.find(screeningId) ?: return
        val dailySchedule = screening.schedule.dailySchedules[datePosition]
        date = dailySchedule.date
        time = dailySchedule.times[timePosition]
        view.initialize(screening.toUiModel(), seats.map { it.toUiModel() })
    }

    fun saveReservation(
        seatList: MutableList<String>,
        price: Long,
    ) {
        val ticketId =
            MockTicketRepository.save(
                screening.movie,
                schedule = LocalDateTime.of(date, time),
                seats = seatList.toList(),
                price = price,
            )
        view.navigateToReservationCompleted(ticketId)
    }

    private val seats: List<Seat> =
        listOf(
            Seat("A", 1),
            Seat("A", 2),
            Seat("A", 3),
            Seat("A", 4),
            Seat("B", 1),
            Seat("B", 2),
            Seat("B", 3),
            Seat("B", 4),
            Seat("C", 1),
            Seat("C", 2),
            Seat("C", 3),
            Seat("C", 4),
            Seat("D", 1),
            Seat("D", 2),
            Seat("D", 3),
            Seat("D", 4),
            Seat("E", 1),
            Seat("E", 2),
            Seat("E", 3),
            Seat("E", 4),
        )
}
