package woowacourse.movie.presenter.contract

import woowacourse.movie.adapter.MovieAdapter
import woowacourse.movie.model.Movie

interface MovieAdapterContract {
    fun assignInitialView(
        movie: Movie,
        itemView: MovieAdapter.MovieViewHolder,
    )
}
