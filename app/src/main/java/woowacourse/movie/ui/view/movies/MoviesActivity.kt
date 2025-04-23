package woowacourse.movie.ui.view.movies

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import woowacourse.movie.R
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.repository.MovieRepository
import woowacourse.movie.presenter.MoviePresenter
import woowacourse.movie.ui.adapter.MovieAdapter
import woowacourse.movie.ui.view.BaseActivity
import woowacourse.movie.ui.view.booking.BookingActivity

class MoviesActivity :
    BaseActivity(),
    MovieContract.View {
    private lateinit var presenter: MoviePresenter
    private val movieRepository = MovieRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupScreen(R.layout.activity_main)

        presenter = MoviePresenter(this, movieRepository)
        presenter.loadAllMovies()
    }

    override fun showAllMovies(movies: List<Movie>) {
        val adapter =
            MovieAdapter(
                this,
                movieRepository.getAllMovies(),
                onReservationClickListener =
                    { movieId ->
                        val intent = Intent(this, BookingActivity::class.java)
                        intent.putExtra(getString(R.string.movie_info_key), movieId)
                        startActivity(intent)
                    },
            )
        val listView = findViewById<ListView>(R.id.movies)
        listView.adapter = adapter
    }
}
