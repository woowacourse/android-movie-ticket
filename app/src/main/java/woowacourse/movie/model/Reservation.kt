package woowacourse.movie.model

import woowacourse.movie.model.movie.Movie
import java.io.Serializable

class Reservation(
    val movieBrief: Movie,
    private val ticketNum: Int,
) : Serializable {
    fun getCharge(): Int = movieBrief.charge * ticketNum
}
