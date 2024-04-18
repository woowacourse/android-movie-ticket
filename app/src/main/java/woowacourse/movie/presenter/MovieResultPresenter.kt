package woowacourse.movie.presenter

import woowacourse.movie.model.MovieRepository
import woowacourse.movie.model.MovieTicket

class MovieResultPresenter(private val resultContractView: MovieResultContract.View) : MovieResultContract.Presenter {
    private var movieRepository: MovieRepository = MovieRepository()

    override fun display(
        id: Long,
        count: Int,
    ) {
        val movie = movieRepository.getOneById(id)
        resultContractView.onInitView(
            movie?.run {
                MovieTicket(this.title, this.date, count)
            },
        )
    }
}
