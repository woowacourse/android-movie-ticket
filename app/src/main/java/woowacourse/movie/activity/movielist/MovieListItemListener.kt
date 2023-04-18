package woowacourse.movie.activity.movielist

import android.view.View
import woowacourse.movie.model.MovieDTO

interface MovieListItemListener {
    fun onClick(movie: MovieDTO, view: View)
}
