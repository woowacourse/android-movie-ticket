package woowacourse.movie.movie

import java.io.Serializable

data class MovieBookingSeatInfo(
    val movieBookingInfo: MovieBookingInfo,
    val seats: List<String>,
    val totalPrice: String,
) : Serializable {
    companion object {
        val dummyData = MovieBookingSeatInfo(
            MovieBookingInfo.dummyData,
            emptyList(),
            ""
        )
    }
}
