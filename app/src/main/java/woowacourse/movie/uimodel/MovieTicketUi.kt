package woowacourse.movie.uimodel

import movie.data.MovieDetail
import movie.data.TicketCount
import java.io.Serializable
import java.time.LocalDate
import java.time.LocalTime

class MovieTicketUi(
    val totalPrice: Int,
    val count: TicketCount,
    val title: String,
    val date: LocalDate,
    val time: LocalTime,
    val seats: List<String>,
) : Serializable {

    companion object {
        fun of(price: Int, movieDetail: MovieDetail, seats: List<String>): MovieTicketUi {
            return MovieTicketUi(
                price,
                movieDetail.count,
                movieDetail.title,
                movieDetail.date,
                movieDetail.time,
                seats,
            )
        }
    }
}
