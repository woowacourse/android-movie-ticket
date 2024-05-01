package woowacourse.movie.list.presenter

import woowacourse.movie.common.AdvertisementDataSource
import woowacourse.movie.common.MovieDataSource
import woowacourse.movie.list.contract.MovieListContract

class MovieListPresenter(
    val view: MovieListContract.View,
) : MovieListContract.Presenter {
    override val movieList = MovieDataSource.movieList
    private val advertisementList = AdvertisementDataSource.advertisementList

    override fun setMoviesInfo() {
        view.showMoviesInfo(movieList, advertisementList)
    }

    override fun setListViewClickListenerInfo() {
        view.setOnListViewClickListener()
    }
}
