package woowacourse.movie.movie

import java.io.Serializable

data class MovieBookingInfo(
    val movieInfo: Movie,
    val date: String,
    val time: String,
    val ticketCount: Int
) : Serializable {

    fun formatBookingTime(): String {
        val formattedDate: String = date.split("-").joinToString(".")
        return "$formattedDate $time"
    }

    companion object {
        val dummyData = MovieBookingInfo(
            Movie.dummyData,
            "",
            "",
            0
        )
    }
}
