package woowacourse.movie.movieList

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.ErrorHandler
import woowacourse.movie.R
import woowacourse.movie.booking.BookingActivity
import woowacourse.movie.uiModel.MovieInfoUIModel
import woowacourse.movie.uiModel.MovieListItem

class MovieListActivity :
    AppCompatActivity(),
    MovieListContract.View {
    private val adapter =
        MovieListAdapter(
            onClick = { movie ->
                if (movie is MovieInfoUIModel) {
                    navigateToBooking(movie)
                }
            },
        )

    private val presenter: MovieListPresenter = MovieListPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.movie_list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        presenter.loadMovies()
    }

    override fun showMovies(movies: List<MovieListItem>) {
        adapter.updateItems(movies)
    }

    override fun navigateToBooking(movie: MovieInfoUIModel) {
        val intent =
            Intent(this, BookingActivity::class.java).apply {
                putExtra(MOVIE_INFO_KEY, movie)
            }
        startActivity(intent)
    }

    override fun showError() {
        ErrorHandler.printError(this)
        finish()
    }

    companion object {
        const val MOVIE_INFO_KEY = "MOVIE_INFO"
    }
}
