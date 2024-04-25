package woowacourse.movie.model

import woowacourse.movie.model.movie.Movie
import java.io.Serializable

class Reservation(
    val movie: Movie,
    private val ticketNum: Int,
) : Serializable {
    fun getCharge(): Int = movie.charge * ticketNum
}
