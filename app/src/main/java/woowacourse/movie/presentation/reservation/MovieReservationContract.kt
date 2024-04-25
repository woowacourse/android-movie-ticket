package woowacourse.movie.presentation.reservation

import woowacourse.movie.domain.model.Movie
import woowacourse.movie.presentation.reservation.model.TicketModel
import java.time.LocalDate

interface MovieReservationContract {
    interface View {
        fun showMovie(movie: Movie)

        fun showCurrentResultTicketCountView()

        fun showDate()
        fun showTime()

        fun showSelectedDate()
        fun showSelectedTime()

        fun moveToTicketDetail(ticketModel: TicketModel)

        fun requestTicketCount(count: (Int)->Unit)

    }

    interface Presenter {
        fun loadMovie()
        fun loadDate()
        fun loadTime()

        fun decreaseTicketCount()

        fun increaseTicketCount()

        fun getTicketCount(): Int

        fun ticketing(
            title : String,
            screeningDate : LocalDate,
            count : Int,
            price : Int,
        )

        fun selectDate()
        fun selectTime()
    }
}
