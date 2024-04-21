package woowacourse.movie.presenter

import woowacourse.movie.model.Movie
import woowacourse.movie.R

class MainPresenterImpl(private val view: MainContract.View) : MainContract.Presenter {
    override fun fetchMovies() {
        val dummy = (0..10000).map {
            Movie(
                poster = R.drawable.poster,
                title = "해리 포터 $it",
                date = "2024-04-12",
                runTime = "152분"
            )
        }
        view.updateMovieList(dummy)
    }
}
