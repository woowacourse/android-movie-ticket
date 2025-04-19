package woowacourse.movie

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.databinding.ActivityMainBinding
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.Movies

class MovieTicketActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val movieListView = binding.movies
        movieListAdapter(movieListView)
    }

    private fun movieListAdapter(movieListView: ListView) {
        val movies = Movies.value
        val movieListAdapter = MovieListAdapter(movies, ::navigateToReservationComplete)
        movieListView.adapter = movieListAdapter
    }

    private fun navigateToReservationComplete(movie: Movie) {
        val intent = Intent(this, ReservationActivity::class.java)
                .apply { putExtra(KEY_MOVIE, movie) }
        startActivity(intent)
    }

    companion object {
        const val KEY_MOVIE = "movie"
    }
}
