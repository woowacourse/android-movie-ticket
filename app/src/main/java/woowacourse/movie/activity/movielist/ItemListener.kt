package woowacourse.movie.activity.movielist

import android.content.Intent
import android.view.View
import woowacourse.movie.activity.moviedetail.MovieDetailActivity
import woowacourse.movie.model.MovieModel

interface ItemListener {
    fun onClick(movie: MovieModel, view: View) {
        val intent = Intent(view.context, MovieDetailActivity::class.java)
        intent.putExtra(MovieDetailActivity.MOVIE_KEY, movie)
        view.context.startActivity(intent)
    }
}
