package woowacourse.movie.presenter

import woowacourse.movie.model.MovieRepository
import woowacourse.movie.model.MovieTicket
import woowacourse.movie.utils.MovieErrorCode

class MovieResultPresenter(private val resultContractView: MovieResultContract.View) : MovieResultContract.Presenter {
    private var movieRepository: MovieRepository = MovieRepository()

    override fun display(
        id: Long,
        count: Int,
    ) {
        val movie = movieRepository.getOneById(id)
        movie?.run {
            resultContractView.onInitView(MovieTicket(this.title, this.date, count))
        } ?: resultContractView.onError(MovieErrorCode.INVALID_MOVIE_ID)
    }
}
