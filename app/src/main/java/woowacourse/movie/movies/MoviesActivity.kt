package woowacourse.movie.movies

import android.os.Bundle
import android.util.Log
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.booking.detail.BookingDetailActivity
import woowacourse.movie.domain.movies

class MoviesActivity :
    AppCompatActivity(),
    MoviesContract.View {
    private lateinit var presenter: MoviesContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = MoviesPresenter(this)
        setupView()
        presenter.loadMovies()
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

    override fun showMovies(uiModels: List<MovieUiModel>) {
        val moviesAdapter =
            MoviesAdapter(uiModels) { movieUiModel ->
                presenter.onMovieSelected(movieUiModel)
            }
        findViewById<ListView>(R.id.lv_movies).adapter = moviesAdapter
    }

    override fun navigateToBookingDetail(movieUiModel: MovieUiModel) {
        val intent = BookingDetailActivity.newIntent(this, movieUiModel)
        Log.d("MoviesActivity", "navigateToBookingDetail called with ${movieUiModel.title}")

        startActivity(intent)
    }
}
