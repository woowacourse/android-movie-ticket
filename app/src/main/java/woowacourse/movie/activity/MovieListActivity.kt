package woowacourse.movie.activity

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.adapter.MovieAdapter
import woowacourse.movie.contract.MovieListContract
import woowacourse.movie.model.movie.Movie
import woowacourse.movie.presenter.MovieListPresenter

class MovieListActivity : AppCompatActivity(), MovieListContract.View {
    private lateinit var movieListView: ListView
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_list_activity)
        val presenter =
            MovieListPresenter(
                view = this,
            )
        presenter.loadMovies()
        movieListView = findViewById(R.id.movies_list_item)
        movieListView.adapter = movieAdapter
    }

    override fun displayMovies(movies: List<Movie>) {
        movieAdapter = MovieAdapter(this)
        movieAdapter.setMovies(movies)
    }

    override fun navigateToMovieDetail(movieId: Int) {
        val intent =
            Intent(this, MovieDetailActivity::class.java).apply {
                putExtra("MovieId", movieId)
            }
        startActivity(intent)
    }
}
