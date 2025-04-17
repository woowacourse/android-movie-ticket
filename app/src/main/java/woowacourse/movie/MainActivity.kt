package woowacourse.movie

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.dao.MovieDao
import woowacourse.movie.databinding.ActivityMainBinding
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.Movies
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val binding = ActivityMainBinding.inflate(layoutInflater)
        val movies = Movies.movies

        val movieListView = binding.movies
        val movieListAdapter = MovieListAdapter(movies, ::navigateToReservationComplete)
        movieListView.adapter = movieListAdapter
    }

    private fun navigateToReservationComplete(movie: Movie) {
        val intent = Intent(this, ReservationActivity::class.java)
                .apply { putExtra("movie", movie) }
        startActivity(intent)
    }
}
