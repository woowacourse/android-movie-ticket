package woowacourse.movie.presenter.contract

import android.view.View
import woowacourse.movie.model.Movie

interface MovieAdapterContract {
    fun assignInitialView(
        movie: Movie,
        itemView: View,
    )
}
