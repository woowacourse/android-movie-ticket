package woowacourse.movie.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.data.MovieRepositoryImpl
import woowacourse.movie.model.MovieRes
import woowacourse.movie.ui.reservation.MovieDetailActivity

class MainActivity : AppCompatActivity() {
    private val movieListView: ListView by lazy { findViewById(R.id.listView) }
    private val adapter: MovieAdapter by lazy {
        MovieAdapter(
            MovieRepositoryImpl.allMovies(),
            object : MovieAdapter.ReservationClickListener {
                override fun onClick(position: Int) {
                    navigateMovieDetail(adapter.movie[position])
                }
            }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        movieListView.adapter = adapter
    }

    private fun navigateMovieDetail(movie: MovieRes) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra(KEY_MOVIE, movie)
        startActivity(intent)
    }

    companion object {
        internal const val KEY_MOVIE = "key_movie"
    }
}
