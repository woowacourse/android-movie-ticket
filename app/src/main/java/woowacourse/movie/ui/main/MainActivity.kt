package woowacourse.movie.ui.main

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.movie.MovieRepository
import woowacourse.movie.ui.booking.BookingActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initAdapter()
    }

    private fun initAdapter() {
        val movieAdapter = MovieAdapter(this) { clickBook(it) }
        findViewById<ListView>(R.id.listMainMovie).adapter = movieAdapter
        movieAdapter.initMovies(MovieRepository.getMovies())
    }

    private fun clickBook(movieId: Long) {
        startActivity(BookingActivity.getIntent(this, movieId))
    }
}
