package woowacourse.movie.feature.movies.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.feature.bookingdetail.view.BookingDetailActivity
import woowacourse.movie.feature.model.MovieUiModel
import woowacourse.movie.feature.movies.contract.MoviesContract
import woowacourse.movie.feature.movies.presenter.MoviesPresenter
import woowacourse.movie.feature.movies.view.adapter.Item
import woowacourse.movie.feature.movies.view.adapter.MoviesAdapter

class MoviesActivity :
    AppCompatActivity(),
    MoviesContract.View {
    private val presenter: MoviesContract.Presenter by lazy { MoviesPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()
        presenter.onCreateView()
    }

    override fun showMovies(movies: List<MovieUiModel>) {
        val moviesAdapter = MoviesAdapter { movie -> presenter.onMovieBookingClicked(movie) }
        moviesAdapter.submitList(Item.from(movies))
        findViewById<RecyclerView>(R.id.rv_movies).adapter = moviesAdapter
    }

    override fun navigateToBookingDetail(movie: MovieUiModel) {
        val intent = BookingDetailActivity.newIntent(this, movie)
        startActivity(intent)
    }

    private fun setupView() {
        enableEdgeToEdge()
        setContentView(R.layout.activity_movies)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_movies)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
