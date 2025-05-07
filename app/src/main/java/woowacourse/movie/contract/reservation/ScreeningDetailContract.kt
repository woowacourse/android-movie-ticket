package woowacourse.movie.contract.reservation

import woowacourse.movie.domain.reservation.Screening
import java.time.LocalDate
import java.time.LocalTime

interface ScreeningDetailContract {
    interface Presenter {
        val ticketCount: Int
        var timeItemPosition: Int

        fun fetchScreeningDetail()

        fun fetchAvailableTimes(date: LocalDate)

        fun plusTicketCount()

        fun minusTicketCount()

        fun fetchAvailableSeats()
    }

    interface View {
        fun setScreeningDetail(screening: Screening)

        fun setTicketCount(count: Int)

        fun setAvailableTimes(
            times: List<LocalTime>,
            timeItemPosition: Int,
        )

        fun navigateToSeatSelectionScreen(
            title: String,
            ticketCount: Int,
        )
    }
}
