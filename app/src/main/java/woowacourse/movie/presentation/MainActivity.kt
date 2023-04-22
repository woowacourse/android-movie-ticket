package woowacourse.movie.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.MovieItemData
import woowacourse.movie.R

class MainActivity : AppCompatActivity() {
    private val movieItemAdapter by lazy { MovieItemAdapter(MOVIE_ITEM_MODELS) { clickBook(it) } }
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
        private val MOVIE_ITEM_MODELS = MovieItemData.getMovieModelList()
    }
}
