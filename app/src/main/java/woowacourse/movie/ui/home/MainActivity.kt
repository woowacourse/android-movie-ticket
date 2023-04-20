package woowacourse.movie.ui.home

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.domain.MovieData
import woowacourse.movie.ui.booking.BookingActivity

class MainActivity : AppCompatActivity() {
    private val movieAdapter by lazy { MovieAdapter(this) { clickBook(it) } }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<ListView>(R.id.listMainMovie).adapter = movieAdapter
        movieAdapter.initMovies(MovieData.movies)
    }

    private fun clickBook(movieId: Long) {
        startActivity(BookingActivity.getIntent(this, movieId))
    }
}
