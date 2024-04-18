package woowacourse.movie.main

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.adapter.ListViewAdapter
import woowacourse.movie.model.Movie
import woowacourse.movie.model.MovieRepository
import woowacourse.movie.reservation.ReservationActivity

class MainActivity : AppCompatActivity(), MainContract.View {
    private val mainPresenter = MainPresenter(this, MovieRepository())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainPresenter.onViewCreated()
    }

    override fun displayMovies(movies: List<Movie>) {
        val movieListView = findViewById<ListView>(R.id.list_view)
        movieListView.adapter =
            ListViewAdapter(movies) { position ->
                mainPresenter.onMovieSelected(position)
            }
    }

    override fun navigateToReservation(movie: Movie) {
        Intent(this, ReservationActivity::class.java).apply {
            putExtra(EXTRA_MOVIE, movie)
            startActivity(this)
        }
    }

    companion object {
        const val EXTRA_MOVIE = "movie"
    }
}
