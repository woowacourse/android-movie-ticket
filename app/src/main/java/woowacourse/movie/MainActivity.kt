package woowacourse.movie

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.ReservationActivity.Companion.MOVIE_DATA_KEY
import woowacourse.movie.model.Movie

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

        setupMovieAdapter()
    }

    private fun setupMovieAdapter() {
        val movieListView = findViewById<ListView>(R.id.lv_main_movies)
        movieListView.adapter =
            MovieAdapter(
                this,
                Movie.values,
                ::navigateToReservationComplete,
            )
    }

    private fun navigateToReservationComplete(movie: Movie) {
        val intent =
            Intent(this, ReservationActivity::class.java).apply { putExtra(MOVIE_DATA_KEY, movie) }
        startActivity(intent)
    }
}
