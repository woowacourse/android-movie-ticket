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
        val movies = Movies.movies

        val movieListView = findViewById<ListView>(R.id.movies)
        val movieListAdapter = MovieListAdapter(movies, ::navigateToReservationComplete)
        movieListView.adapter = movieListAdapter
    }

    private fun navigateToReservationComplete(movie: Movie) {
        val bundle =
            Bundle().apply {
                putString(ReservationCompleteActivity.MOVIE_TITLE_KEY, movie.title)
                putString(
                    ReservationCompleteActivity.MOVIE_SCREENING_DATE_KEY,
                    movie.startDateTime.format(DateTimeFormatter.ofPattern("yyyy.MM.dd")),
                )
            }
        val intent =
            Intent(this, ReservationCompleteActivity::class.java).apply { putExtras(bundle) }
        startActivity(intent)
    }
}
