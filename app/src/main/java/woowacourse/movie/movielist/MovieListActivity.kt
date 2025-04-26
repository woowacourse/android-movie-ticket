package woowacourse.movie.movielist

import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.detailbooking.DetailBookingActivity
import woowacourse.movie.domain.Movie

class MovieListActivity : AppCompatActivity(), MovieListContract.View {
    private val movieListPresenter = MovieListPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_movie_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.layout_movie_list_root)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        movieListPresenter.updateMovies()
    }

    override fun showMovieList(movies: List<Movie>) {
        val listView: ListView = findViewById(R.id.movie_listview)
        val movieListAdapter =
            MovieListAdapter(
                movies,
                object : ClickListener {
                    override fun onReserveClick(movie: Movie) {
                        clickedButton(movie)
                    }
                },
            )
        listView.adapter = movieListAdapter
    }

    override fun clickedButton(movie: Movie) {
        startActivity(DetailBookingActivity.newIntent(this@MovieListActivity, movie))
    }
}
