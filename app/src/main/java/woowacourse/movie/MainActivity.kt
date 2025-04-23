package woowacourse.movie

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.BookingActivity.Companion.KEY_MOVIE_DATA
import woowacourse.movie.model.Movie
import woowacourse.movie.model.adapter.MovieAdapter
import java.time.LocalDate

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        setUpUi()

        val intentMovieData =
            movieOrNull() ?: mockData()

        val movieList = listOf(intentMovieData)
        val movieAdapter =
            MovieAdapter(resources, movieList) { movie ->
                startBookingActivity(movie)
            }
        val listView = findViewById<ListView>(R.id.listview_layout)

        listView.adapter = movieAdapter
    }

    private fun setUpUi() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun movieOrNull(): Movie? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(KEY_MOVIE_DATA, Movie::class.java)
        } else {
            intent.getParcelableExtra(KEY_MOVIE_DATA)
        }
    }

    private fun startBookingActivity(movie: Movie) {
        val intent =
            Intent(this, BookingActivity::class.java).apply {
                putExtra(KEY_MOVIE_DATA, movie)
            }
        startActivity(intent)
    }

    private fun mockData(): Movie {
        return Movie(
            imageSource = "harry_potter.png",
            title = "해리 포터와 마법사의 돌",
            runningTime = 152,
            screeningStartDate = LocalDate.of(2025, 4, 1),
            screeningEndDate = LocalDate.of(2025, 4, 25),
        )
    }
}
