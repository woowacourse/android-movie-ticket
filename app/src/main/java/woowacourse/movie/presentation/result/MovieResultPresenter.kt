package woowacourse.movie.presentation.result

import woowacourse.movie.domain.MovieTicket
import woowacourse.movie.utils.MovieErrorCode

class MovieResultPresenter(private val resultContractView: MovieResultContract.View) :
    MovieResultContract.Presenter {
    private var movieRepository: io.pyron.server.data.dao.MovieDao =
        io.pyron.server.data.dao.MovieDao()

    override fun display(
        id: Long,
        count: Int,
    ) {
        val movie = movieRepository.findOneById(id)
        movie?.run {
            resultContractView.onInitView(MovieTicket(this.title, 123, count))
        } ?: resultContractView.onError(MovieErrorCode.INVALID_MOVIE_ID)
    }
}
