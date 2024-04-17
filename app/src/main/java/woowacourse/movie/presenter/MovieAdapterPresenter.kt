package woowacourse.movie.presenter

import android.view.View
import woowacourse.movie.model.Movie
import woowacourse.movie.presenter.contract.MovieAdapterContract

class MovieAdapterPresenter(private val movieListContractView: MovieAdapterContract) {
    fun assignInitialView(
        movie: Movie,
        itemView: View,
    ) {
        movieListContractView.assignInitialView(movie, itemView)
    }
}
