package woowacourse.movie.presentation

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.MovieData
import woowacourse.movie.R
import woowacourse.movie.presentation.model.toPresentation

class MainActivity : AppCompatActivity() {
    private val movieAdapter by lazy { MovieAdapter(this) { clickBook(it) } }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<ListView>(R.id.listMainMovie).adapter = movieAdapter
        movieAdapter.initMovies(MovieData.movies.map { it.toPresentation() })
    }

    private fun clickBook(movieId: Long) {
        startActivity(BookingActivity.getIntent(this, movieId))
    }
}
