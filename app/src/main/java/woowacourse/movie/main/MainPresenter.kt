package woowacourse.movie.main

import woowacourse.movie.DefaultMovies

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {
    override fun initMovies() {
        view.showMovies(DefaultMovies.movies)
    }
}
