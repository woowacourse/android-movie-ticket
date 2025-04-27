package woowacourse.movie.movies

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.booking.detail.BookingDetailActivity

class MoviesActivity :
    AppCompatActivity(),
    MoviesContract.View {
    private lateinit var presenter: MoviesContract.Presenter
    private lateinit var moviesRecyclerView: RecyclerView
    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()

        presenter = MoviesPresenter(this)
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
        moviesRecyclerView = findViewById(R.id.movies_view)
    }

    override fun showMovies(uiModels: List<MovieUiModel>) {
        moviesAdapter =
            MoviesAdapter(uiModels) { movieUiModel ->
                presenter.onMovieSelected(movieUiModel)
            }
        moviesRecyclerView.adapter = moviesAdapter
    }

    override fun navigateToBookingDetail(movieUiModel: MovieUiModel) {
        val intent = BookingDetailActivity.newIntent(this, movieUiModel)
        Log.d("MoviesActivity", "navigateToBookingDetail called with ${movieUiModel.title}")

        startActivity(intent)
    }
}
