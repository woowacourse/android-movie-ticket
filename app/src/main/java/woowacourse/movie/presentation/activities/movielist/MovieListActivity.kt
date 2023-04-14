package woowacourse.movie.presentation.activities.movielist

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.databinding.ActivityMovieListBinding
import woowacourse.movie.presentation.activities.ticketing.TicketingActivity
import woowacourse.movie.presentation.model.Movie

class MovieListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieListBinding
    private lateinit var movieListAdapter: MovieListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setMovieListAdapter()
    }

    private fun setMovieListAdapter() {
        movieListAdapter =
            MovieListAdapter(Movie.provideDummy()) { movie ->
                val intent = Intent(this, TicketingActivity::class.java).putExtra(MOVIE_KEY, movie)
                startActivity(intent)
            }
        binding.lvMovies.adapter = movieListAdapter
    }

    companion object {
        internal const val MOVIE_KEY = "movie_key"
    }
}
