package woowacourse.movie.presenter

import woowacourse.movie.MOVIES
import woowacourse.movie.adapter.MovieAdapter
import woowacourse.movie.presenter.contract.MovieListContract

class MovieListPresenter(private val movieListContractView: MovieListContract.View) : MovieListContract.Presenter {
    private val movies = MOVIES

    override fun getAdapter(): MovieAdapter = MovieAdapter(movies, movieListContractView::navigateToTicketing)
}
