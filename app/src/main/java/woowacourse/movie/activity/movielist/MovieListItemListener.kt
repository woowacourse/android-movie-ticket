package woowacourse.movie.activity.movielist

import android.view.View
import woowacourse.movie.model.MovieModel

interface MovieListItemListener {
    fun onClick(movie: MovieModel, view: View)
}
