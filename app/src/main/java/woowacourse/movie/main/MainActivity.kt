package woowacourse.movie.main

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.KEY_MOVIE
import woowacourse.movie.Movie
import woowacourse.movie.R
import woowacourse.movie.data.MovieRepositories
import woowacourse.movie.reservation.MovieDetailActivity

class MainActivity : AppCompatActivity() {
    private val movieListView: ListView by lazy { findViewById(R.id.listView) }
    private val adapter: MovieAdapter by lazy { MovieAdapter(initMovieData()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initMovieListView()
    }

    private fun initMovieListView() {
        movieListView.adapter = adapter
        adapter.clickListener = object : MovieAdapter.ReservationClickListener {
            override fun onClick(position: Int) {
                val intent = Intent(this@MainActivity, MovieDetailActivity::class.java)
                intent.putExtra(KEY_MOVIE, adapter.movie[position])
                startActivity(intent)
            }
        }
    }

    private fun initMovieData(): List<Movie> {
        return MovieRepositories().movieRepositories
    }
}
