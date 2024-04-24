package woowacourse.movie.presentation.reservation

import woowacourse.movie.domain.model.Movie
import woowacourse.movie.presentation.reservation.model.TicketModel
import java.time.LocalDate

interface MovieReservationContract {
    interface View {
        fun showMovie(movie: Movie)

        fun showCurrentResultTicketCountView()

        fun moveToTicketDetail(ticketModel: TicketModel)
    }

    interface Presenter {
        fun loadMovie()

        fun decreaseTicketCount()

        fun increaseTicketCount()

        fun getTicketCount(): Int

        fun ticketing(
            title : String,
            screeningDate : LocalDate,
            count : Int,
            price : Int,
        )
    }
}
