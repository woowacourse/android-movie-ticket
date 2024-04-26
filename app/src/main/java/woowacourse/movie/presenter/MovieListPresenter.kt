package woowacourse.movie.presenter

import woowacourse.movie.adapter.ScreeningAdapter
import woowacourse.movie.model.MovieData.SCREENING_DATA
import woowacourse.movie.presenter.contract.MovieListContract

class MovieListPresenter(private val movieListContractView: MovieListContract.View) : MovieListContract.Presenter {
    private val screenings = SCREENING_DATA

    override fun getAdapter(): ScreeningAdapter = ScreeningAdapter(screenings, movieListContractView::navigateToTicketing)
}
