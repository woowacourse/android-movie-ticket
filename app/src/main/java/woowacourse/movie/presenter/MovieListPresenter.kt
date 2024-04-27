package woowacourse.movie.presenter

import woowacourse.movie.model.MovieData.ADVERTISEMENT_DRAWABLE_ID
import woowacourse.movie.model.MovieData.SCREENING_DATA
import woowacourse.movie.presenter.contract.MovieListContract

class MovieListPresenter(private val movieListContractView: MovieListContract.View) : MovieListContract.Presenter {
    override fun loadScreeningData() {
        movieListContractView.initializeScreeningList(SCREENING_DATA, ADVERTISEMENT_DRAWABLE_ID)
    }

    override fun startReservation(screeningId: Long) {
        movieListContractView.navigateToTicketing(screeningId)
    }
}
