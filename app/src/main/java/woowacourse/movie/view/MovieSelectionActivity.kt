package woowacourse.movie.view

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.domain.Movie
import java.time.LocalDate

class MovieSelectionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeView()

        val movies: List<Movie> =
            (1..10000).map { n ->
                Movie(
                    "해리 포터 $n",
                    startDate = LocalDate.of(2025, 4, 1),
                    endDate = LocalDate.of(2025, 4, 25),
                    runningTime = 152,
                    poster = R.drawable.harry_potter_poster,
                )
            }

        val movieListView = findViewById<ListView>(R.id.movie_list)
        val movieAdapter = MovieAdapter(this, movies) { movie -> reserveMovie(movie) }
        movieListView.adapter = movieAdapter
    }

    private fun initializeView() {
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun reserveMovie(movie: Movie) {
        val intent = Intent(this, MovieReservationActivity::class.java)
        intent.putExtra(MovieAdapter.KEY_MOVIE, movie)
        startActivity(intent)
    }
}
