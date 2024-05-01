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
    private val quantity: Int,
) : SeatSelectionContract.Presenter {
    private lateinit var screening: Screening
    private val seats: List<Seat> by lazy { MockScreeningRepository.getSeats(screeningId) }
    private val selectedSeatList: MutableList<Seat> = emptyList<Seat>().toMutableList()
    private var price: Long = 0

    override fun fetchData() {
        screening = MockScreeningRepository.find(screeningId) ?: return
        val seats = MockScreeningRepository.getSeats(screeningId)
        view.initialize(screening.toUiModel(), seats.map { it.toUiModel() })
    }

    override fun proceedReservation() {
        if (selectedSeatList.size == quantity) {
            view.confirmReservation()
        } else {
            view.noticeReservationImpossible(quantity)
        }
    }

    override fun proceedSeatSelection(index: Int) {
        val selectedSeat = seats[index]
        if (selectedSeat in selectedSeatList) {
            removeSeat(selectedSeat, index)
        } else {
            if (selectedSeatList.size < quantity) {
                addSeat(selectedSeat, index)
            } else {
                view.noticeReservationImpossible(quantity)
            }
        }
    }

    private fun removeSeat(
        selectedSeat: Seat,
        index: Int,
    ) {
        selectedSeatList.remove(selectedSeat)
        price -= selectedSeat.price
        view.updatePriceTextView(price)
        view.checkSeatSelected(index)
    }

    private fun addSeat(
        selectedSeat: Seat,
        index: Int,
    ) {
        selectedSeatList.add(selectedSeat)
        price += selectedSeat.price
        view.updatePriceTextView(price)
        view.checkSeatSelected(index)
    }

    override fun saveTicket() {
        val dailySchedule = screening.schedule.dailySchedules[datePosition]
        val ticketId =
            MockTicketRepository.save(
                movie = screening.movie,
                schedule =
                    LocalDateTime.of(
                        dailySchedule.date,
                        dailySchedule.times[timePosition],
                    ),
                seats = selectedSeatList,
                price = price,
            )
        view.navigateToReservationCompleted(ticketId)
    }
}
