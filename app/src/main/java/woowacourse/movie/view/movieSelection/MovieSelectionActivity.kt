package woowacourse.movie.view.movieSelection

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.presenter.movieSelection.MovieSelectionContract
import woowacourse.movie.presenter.movieSelection.MovieSelectionPresenter
import woowacourse.movie.view.model.MovieListItem
import woowacourse.movie.view.model.MovieListItem.MovieUiModel
import woowacourse.movie.view.movieReservation.MovieReservationActivity

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
        val movieListView = findViewById<RecyclerView>(R.id.movie_list)
        movieListView.layoutManager = LinearLayoutManager(this)
        val adapter = MovieAdapter { movie -> presenter.onMovieSelection(movie) }
        movieListView.adapter = adapter
        adapter.submitList(movies)
    }

    override fun selectMovie(movie: MovieUiModel) {
        val intent = MovieReservationActivity.createIntent(this, movie)
        startActivity(intent)
    }
}
