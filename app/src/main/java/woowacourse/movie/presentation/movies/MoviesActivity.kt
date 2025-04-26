package woowacourse.movie.presentation.movies

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import woowacourse.movie.R
import woowacourse.movie.domain.model.movie.Movie
import woowacourse.movie.domain.model.movie.ScreeningMovies
import woowacourse.movie.presentation.booking.BookingActivity
import woowacourse.movie.ui.BaseActivity
import woowacourse.movie.ui.adapter.MovieAdapter
import woowacourse.movie.ui.constant.IntentKeys

class MoviesActivity : BaseActivity(), MoviesContract.View {
    override val layoutRes: Int
        get() = R.layout.activity_movies

    private lateinit var moviesPresenter: MoviesPresenter
    private val moviesView: ListView by lazy { findViewById(R.id.listview_movies) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupScreen(layoutRes)
        moviesPresenter = MoviesPresenter(this, ScreeningMovies())
        moviesPresenter.onViewCreated()
    }

    override fun showMovies(movies: List<Movie>) {
        moviesView.adapter = MovieAdapter(this, movies) { movie ->
            moviesPresenter.onMovieClicked(movie)
        }
    }

    override fun navigateToBooking(movie: Movie) {
        val intent = Intent(this, BookingActivity::class.java).apply {
            putExtra(IntentKeys.MOVIE, movie)
        }
        startActivity(intent)
    }
}
