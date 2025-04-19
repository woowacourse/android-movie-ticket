package woowacourse.movie.view.movie

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.model.Movie
import woowacourse.movie.view.Extras
import woowacourse.movie.view.MovieClickListener
import woowacourse.movie.view.reservation.ReservationActivity

class MoviesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_movies)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupMovieAdapter()
    }

    private fun setupMovieAdapter() {
        val movieListView = findViewById<ListView>(R.id.lv_movies)
        movieListView.adapter =
            MovieAdapter(
                this,
                Movie.values,
                object : MovieClickListener {
                    override fun onReservationClick(movie: Movie) {
                        navigateToReservationComplete(movie)
                    }
                },
            )
    }

    private fun navigateToReservationComplete(movie: Movie) {
        val intent =
            Intent(
                this,
                ReservationActivity::class.java,
            ).apply { putExtra(Extras.MovieData.MOVIE_KEY, movie) }
        startActivity(intent)
    }
}
