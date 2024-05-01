package io.pyron.server.data.dto

import io.pyron.server.data.entity.MovieSeat
import io.pyron.server.data.entity.Tier

class MovieSeatResponse(
    val id: Long,
    val movieSeatBoardId: Long,
    val number: Int,
    val tier: Tier,
) {
    companion object {
        fun fromMovieSeat(movieSeat: MovieSeat): MovieSeatResponse {
            return MovieSeatResponse(
                id = movieSeat.id,
                movieSeatBoardId = movieSeat.movieSeatBoardId,
                number = movieSeat.number,
                tier = movieSeat.tier,
            )
        }
    }
}
