package woowacourse.movie.ui.movielist

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.woowacourse.movie.domain.Movie
import woowacourse.movie.R
import woowacourse.movie.model.MovieUI
import woowacourse.movie.model.mapper.toMovieUI
import woowacourse.movie.ui.movielist.adapter.MovieListAdapter
import woowacourse.movie.ui.ticketing.TicketingActivity

class MovieListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
        initMovieData()
    }

    private fun initMovieData() {
        val movieData = initMovieThumbnail()
        setAdapter(movieData)
    }

    private fun initMovieThumbnail(): List<MovieUI> =
        runCatching {
            Movie.provideDummy().map { it.toMovieUI() }
        }
            .getOrDefault(emptyList())

    private fun setAdapter(movies: List<MovieUI>) {
        val recyclerMovies: RecyclerView = findViewById(R.id.recycler_movies)
        val movieAdapter = MovieListAdapter(movies, ::onBookClick)
        recyclerMovies.adapter = movieAdapter
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
