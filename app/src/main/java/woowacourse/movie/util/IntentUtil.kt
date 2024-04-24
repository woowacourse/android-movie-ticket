package woowacourse.movie.util

import android.content.Intent
import android.os.Build
import woowacourse.movie.list.model.Movie
import woowacourse.movie.list.model.MovieDataSource
import woowacourse.movie.list.view.MovieListActivity.Companion.EXTRA_MOVIE_KEY

object IntentUtil {
    fun getSerializableMovieData(intent: Intent): Movie {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(EXTRA_MOVIE_KEY, Movie::class.java)
                ?: MovieDataSource.emptyMovie
        } else {
            intent.getSerializableExtra(EXTRA_MOVIE_KEY) as Movie
        }
    }
}
