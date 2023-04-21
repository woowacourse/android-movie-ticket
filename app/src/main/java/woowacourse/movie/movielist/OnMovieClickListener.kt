package woowacourse.movie.movielist

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import woowacourse.movie.dto.MovieDto
import woowacourse.movie.moviedetail.MovieDetailActivity

class OnMovieClickListener(private val context: Context) : OnClickListener<MovieDto> {
    override fun onClick(item: MovieDto) {
        val intent = Intent(context, MovieDetailActivity::class.java)
        intent.putExtra(MOVIE_KEY, item)
        context.startActivity(intent)
    }

    companion object {
        private const val MOVIE_KEY = "movie"
    }
}
