package woowacourse.movie.data.remote

// server
import io.pyron.server.ServerApi
import woowacourse.movie.data.remote.dto.MovieResponse

// 서버로부터 json으로 받는다고 가정!
class FakeRemoteDataSource(private val serverApi: ServerApi = ServerApi()) : RemoteDataSource {
    override fun findAll(): List<MovieResponse> {
        return serverApi.findAllMovieWithDateTimes().map {
            MovieResponse(
                id = it.id,
                thumbnailUrl = it.thumbnailUrl,
                title = it.title,
                description = it.description,
                dateTime = it.dateTime,
                runningTime = it.runningTime,
            )
        }
    }

    override fun findOneById(id: Long): MovieResponse? {
        return serverApi.findOneMovieWithDateTime(id)?.let {
            MovieResponse(
                id = it.id,
                thumbnailUrl = it.thumbnailUrl,
                title = it.title,
                description = it.description,
                dateTime = it.dateTime,
                runningTime = it.runningTime,
            )
        }
    }
}
