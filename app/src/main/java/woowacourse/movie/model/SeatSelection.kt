package woowacourse.movie.model

import woowacourse.movie.selectSeat.SeatPrice
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class SeatSelection(
    private val title: String,
    private val date: LocalDate,
    private val time: LocalTime,
) {
    private val selectedSeats: MutableList<Seat> = mutableListOf()
    private var totalMoney: Int = 0

    val seats: List<Seat> get() = selectedSeats.toList()
    val money: Int get() = totalMoney
    val count: Int get() = selectedSeats.size
    val titleText: String get() = title
    val dateValue: LocalDate get() = date
    val timeValue: LocalTime get() = time

    fun isSeatSelected(seat: Seat): Boolean = seat in selectedSeats

    fun selectSeat(seat: Seat) {
        if (!isSeatSelected(seat)) {
            selectedSeats.add(seat)
            totalMoney += SeatPrice.getPrice(seat)
        }
    }

    fun unSelectSeat(seat: Seat) {
        if (isSeatSelected(seat)) {
            selectedSeats.remove(seat)
            totalMoney -= SeatPrice.getPrice(seat)
        }
    }

    fun isFull(count: Int): Boolean = selectedSeats.size == count

    fun getTotalMoney(): Int = totalMoney

    fun toTicket(): Ticket =
        Ticket(
            title = title,
            date = date,
            time = time,
            seats = selectedSeats.toList(),
            totalPrice = totalMoney,
        )

    companion object {
        private val dateFormatter = DateTimeFormatter.ofPattern("yyyy.M.d")
        private val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

        fun fromUIModel(uiModel: woowacourse.movie.uiModel.TicketUIModel): SeatSelection {
            val date = LocalDate.parse(uiModel.date, dateFormatter)
            val time = LocalTime.parse(uiModel.time, timeFormatter)
            return SeatSelection(uiModel.title, date, time)
        }
    }
}
