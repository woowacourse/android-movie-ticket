package woowacourse.movie.presenter

import woowacourse.movie.model.MovieData.SCREENING_DATA
import woowacourse.movie.presenter.contract.MovieListContract

class MovieListPresenter(private val movieListContractView: MovieListContract.View) : MovieListContract.Presenter {
    init {
        movieListContractView.initializeScreeningList(SCREENING_DATA)
    }

    override fun startReservation(screeningId: Long) {
        movieListContractView.navigateToTicketing(screeningId)
    }
}
