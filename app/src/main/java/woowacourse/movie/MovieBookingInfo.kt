package woowacourse.movie

import java.io.Serializable

data class MovieBookingInfo(
    val movieInfo: Movie,
    val date: String,
    val time: String,
    val ticketCount: Int
) : Serializable {
    companion object {
        val nullData = MovieBookingInfo(
            Movie.nullData,
            "",
            "",
            0
        )
    }
}
