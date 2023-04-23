package woowacourse.movie.movielist.listener

import android.content.Context
import woowacourse.movie.BundleKeys
import woowacourse.movie.activity.MovieDetailActivity
import woowacourse.movie.movie.Movie

class MovieOnClickListener(private val context: Context) : OnClickListener<Movie> {
    override fun onClick(item: Movie) {
        val intent = MovieDetailActivity.intent(context)
        intent.putExtra(BundleKeys.MOVIE_DATA_KEY, item)
        context.startActivity(intent)
    }
}
