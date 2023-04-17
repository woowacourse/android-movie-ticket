package woowacourse.movie.activities.movielist

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.woowacourse.movie.domain.Movie
import woowacourse.movie.R
import woowacourse.movie.activities.movielist.adapter.MovieListAdapter
import woowacourse.movie.activities.ticketing.TicketingActivity
import woowacourse.movie.model.MovieUI
import woowacourse.movie.model.mapper.toMovieUI

class MovieListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
        setMovieData()
    }

    private fun setMovieData() {
        val movieData = setMovieThumbnail()
        setAdapter(movieData)
    }

    private fun setMovieThumbnail(): List<MovieUI> =
        runCatching {
            Movie.provideDummy().map { it.toMovieUI() }
        }
            .getOrDefault(emptyList())

    private fun setAdapter(movies: List<MovieUI>) {
        val lvMovies: ListView = findViewById(R.id.lv_movies)
        val listAdapter = MovieListAdapter(movies) { movie ->
            onBookClick(movie)
        }
        lvMovies.adapter = listAdapter
    }

    private fun onBookClick(item: MovieUI) {
        val intent = Intent(this@MovieListActivity, TicketingActivity::class.java)
        intent.putExtra(MOVIE_KEY, item)
        startActivity(intent)
    }

    companion object {
        internal const val MOVIE_KEY = "movie"
    }
}
