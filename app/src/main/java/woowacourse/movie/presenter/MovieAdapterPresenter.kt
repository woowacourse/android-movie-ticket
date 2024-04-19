package woowacourse.movie.presenter

import woowacourse.movie.adapter.MovieAdapter
import woowacourse.movie.model.Movie
import woowacourse.movie.presenter.contract.MovieAdapterContract

class MovieAdapterPresenter(private val movieListContractView: MovieAdapterContract) {
    fun assignInitialView(
        movie: Movie,
        itemView: MovieAdapter.MovieViewHolder,
    ) {
        movieListContractView.assignInitialView(movie, itemView)
    }
}
