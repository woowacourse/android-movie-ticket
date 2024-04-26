package woowacourse.movie.model.theater

import woowacourse.movie.model.movieInfo.MovieInfo
import java.io.Serializable

class Theater(val movie: MovieInfo, private val seats: Map<String, Seat>) : Serializable {

    fun getSeatCharge(seatId: String): Int {
        return seats[seatId]?.price ?: throw IllegalArgumentException("Seat not found")
    }
}
