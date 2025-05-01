package woowacourse.movie.movielist

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.detailbooking.DetailBookingActivity
import woowacourse.movie.domain.Movie

class MovieListActivity : AppCompatActivity(), MovieListContract.View {
    private val movieListPresenter = MovieListPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_movie_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.movie_recyclerview)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        movieListPresenter.updateMovies()
    }

    override fun showMovieList(movies: List<FeedItem>) {
        val recyclerView: RecyclerView = findViewById(R.id.movie_recyclerview)
        val movieListAdapter =
            MovieListAdapter(
                object : ClickListener {
                    override fun onReserveClick(movie: Movie) {
                        clickedButton(movie)
                    }
                },
                movies,
            )
        recyclerView.adapter = movieListAdapter
    }

    override fun clickedButton(movie: Movie) {
        startActivity(DetailBookingActivity.newIntent(this@MovieListActivity, movie))
    }
}
