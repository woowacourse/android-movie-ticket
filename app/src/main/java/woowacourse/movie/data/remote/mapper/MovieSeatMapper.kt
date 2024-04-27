package woowacourse.movie.data.remote.mapper

import woowacourse.movie.data.remote.dto.MovieSeatResponse
import woowacourse.movie.domain.MovieSeat

object MovieSeatMapper {
    fun fromMovieSeatResponse(movieSeatResponse: MovieSeatResponse): MovieSeat {
        return MovieSeat(
            id = movieSeatResponse.id,
            movieSeatBoardId = movieSeatResponse.movieSeatBoardId,
            number = movieSeatResponse.number,
            tier = TierMapper.fromTierResponse(movieSeatResponse.tier),
        )
    }
}
