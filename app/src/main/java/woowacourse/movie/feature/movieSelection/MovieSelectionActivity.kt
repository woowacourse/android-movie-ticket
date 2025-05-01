package woowacourse.movie.feature.movieSelection

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.feature.model.movie.MovieListItem
import woowacourse.movie.feature.model.movie.MovieListItem.MovieUiModel
import woowacourse.movie.feature.movieReservation.MovieReservationActivity
import woowacourse.movie.feature.movieSelection.adapter.MovieAdapter

class MovieSelectionActivity : AppCompatActivity(), MovieSelectionContract.View {
    private val presenter = MovieSelectionPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeView()
        presenter.loadMovies()
    }

    private fun initializeView() {
        enableEdgeToEdge()
        setContentView(R.layout.activity_movie_selection)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun showMovies(movies: List<MovieListItem>) {
        val movieRecyclerView = findViewById<RecyclerView>(R.id.movie_list)
        val adapter = MovieAdapter { movie -> presenter.selectMovie(movie) }
        movieRecyclerView.adapter = adapter
        adapter.submitList(movies)
    }

    override fun goToReservation(movie: MovieUiModel) {
        val intent = MovieReservationActivity.createIntent(this, movie)
        startActivity(intent)
    }
}
