package woowacourse.movie.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.data.MovieItemData
import woowacourse.movie.presentation.booking.BookingActivity

class MainActivity : AppCompatActivity() {
    private val movieItemAdapter by lazy { MovieItemAdapter(MOVIE_ITEMS) { clickBook(it) } }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setMovieAdapter()
    }

    private fun setMovieAdapter() {
        findViewById<RecyclerView>(R.id.recyclerMainMovie).adapter = movieItemAdapter
    }

    private fun clickBook(movieId: Long) {
        startActivity(BookingActivity.getIntent(this, movieId))
    }

    companion object {
        private val MOVIE_ITEMS = MovieItemData.getMovieItems()
    }
}
