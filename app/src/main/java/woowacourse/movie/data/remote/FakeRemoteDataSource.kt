package woowacourse.movie.data.remote

// server
import io.pyron.server.ServerApi
import io.pyron.server.data.entity.Tier
import woowacourse.movie.data.remote.dto.MovieResponse
import woowacourse.movie.data.remote.dto.MovieSeatResponse
import woowacourse.movie.data.remote.dto.ScreenDateTimeResponse
import woowacourse.movie.data.remote.dto.TierResponse

// 서버로부터 json으로 받는다고 가정!
class FakeRemoteDataSource(private val serverApi: ServerApi = ServerApi()) : RemoteDataSource {
    override fun findAllMovies(): List<MovieResponse> {
        return serverApi.findAllMovieWithDateTimes().map {
            MovieResponse(
                id = it.id,
                thumbnailUrl = it.thumbnailUrl,
                title = it.title,
                description = it.description,
                dateTime =
                    it.screenDateTimes.map {
                        ScreenDateTimeResponse(
                            id = it.id,
                            dateTime = it.dateTime,
                        )
                    },
                runningTime = it.runningTime,
            )
        }
    }

    override fun findMovieById(id: Long): MovieResponse? {
        return serverApi.findOneMovieWithDateTime(id)?.let {
            MovieResponse(
                id = it.id,
                thumbnailUrl = it.thumbnailUrl,
                title = it.title,
                description = it.description,
                dateTime =
                    it.screenDateTimes.map {
                        ScreenDateTimeResponse(
                            id = it.id,
                            dateTime = it.dateTime,
                        )
                    },
                runningTime = it.runningTime,
            )
        }
    }

    override fun findAllSeatsById(movieScreenDateTimeId: Long): List<MovieSeatResponse> {
        return serverApi.findSeatsByScreenDateTime(movieScreenDateTimeId = movieScreenDateTimeId).map {
            MovieSeatResponse(
                id = it.id,
                movieSeatBoardId = it.movieSeatBoardId,
                number = it.number,
                tier =
                    when (it.tier) {
                        Tier.S -> {
                            TierResponse.S
                        }
                        Tier.A -> {
                            TierResponse.A
                        }
                        Tier.B -> {
                            TierResponse.B
                        }
                    },
            )
        }
    }

    override fun findSeatById(seatId: Long): MovieSeatResponse? {
        return serverApi.findSeatById(seatId)?.let {
            MovieSeatResponse(
                id = it.id,
                movieSeatBoardId = it.movieSeatBoardId,
                number = it.number,
                tier =
                    when (it.tier) {
                        Tier.S -> {
                            TierResponse.S
                        }
                        Tier.A -> {
                            TierResponse.A
                        }
                        Tier.B -> {
                            TierResponse.B
                        }
                    },
            )
        }
    }

    override fun findScreenDateTimeByMovieScreenDateTimeId(movieScreenDateTimeId: Long): ScreenDateTimeResponse? {
        return serverApi.findScreenDateTimeByMovieScreenDateTime(movieScreenDateTimeId)?.let {
            ScreenDateTimeResponse(
                id = it.id,
                dateTime = it.dateTime,
            )
        }
    }
}
