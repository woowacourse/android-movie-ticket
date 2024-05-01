package woowacourse.movie.presentation.screening

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.data.MovieRepositoryFactory
import woowacourse.movie.presentation.reservation.booking.MovieReservationActivity
import woowacourse.movie.presentation.screening.adapter.MovieAdapter

class ScreeningMovieActivity : AppCompatActivity(), ScreeningMovieView {
    private lateinit var presenter: ScreeningMoviePresenter
    private lateinit var movieRecyclerView: RecyclerView
    private lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screening_movie)
        initViews()
        presenter =
            ScreeningMoviePresenter(
                this,
                MovieRepositoryFactory.movieRepository(),
            ).apply { loadScreenMovies() }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        presenter.loadScreenMovies()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }

    override fun showMovies(movies: List<ScreeningMovieUiModel>) {
        adapter.submitList(movies)
    }

    override fun navigateToReservationView(movieId: Long) {
        val intent = MovieReservationActivity.newIntent(this, movieId)
        startActivity(intent)
    }

    override fun navigateToAdView() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(AD_URL))
        startActivity(intent)
    }

    override fun showErrorView() {
        val errorLayout = findViewById<LinearLayout>(R.id.cl_screening_movie_error)
        val successLayout = findViewById<RecyclerView>(R.id.rv_screening_movie)
        errorLayout.visibility = View.VISIBLE
        successLayout.visibility = View.GONE
    }

    private fun initViews() {
        movieRecyclerView = findViewById<RecyclerView>(R.id.rv_screening_movie)
        adapter =
            MovieAdapter(
                onClickReservationButton = { presenter.startReservation(it) },
                onClickAd = { presenter.startAd() },
            ).also { movieRecyclerView.adapter = it }
    }

    companion object {
        private const val AD_URL = "https://www.woowacourse.io/"
    }
}
