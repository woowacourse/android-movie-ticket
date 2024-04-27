package woowacourse.movie.feature.seat

import woowacourse.movie.data.MockScreeningRepository
import woowacourse.movie.data.MockTicketRepository
import woowacourse.movie.domain.screening.BasicScreeningScheduleSystem
import woowacourse.movie.domain.screening.Screening
import woowacourse.movie.feature.main.ui.toUiModel
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class SeatSelectionPresenter(
    private val view: SeatSelectionContract.View,
    private val id: Long,
    private val datePosition: Int,
    private val timePosition: Int,
) : SeatSelectionContract.Presenter {
    lateinit var screening: Screening
    lateinit var date: LocalDate
    lateinit var time: LocalTime

    override fun fetchData() {
        screening = MockScreeningRepository.find(id) ?: return
        val dailySchedule =
            BasicScreeningScheduleSystem().getSchedules(
                screening.releaseDate,
                screening.endDate,
            ).dailySchedules[datePosition]
        date = dailySchedule.date
        time = dailySchedule.times[timePosition]
        view.initialize(screening.toUiModel())
    }

    fun saveReservation(seatList: MutableList<String>) {
        val ticketId =
            MockTicketRepository.save(
                screening.movie,
                schedule = LocalDateTime.of(date, time),
                seats = seatList.toList(),
                10000 * seatList.size.toLong(),
            )
        view.navigateToReservationCompleted(ticketId)
    }
}
