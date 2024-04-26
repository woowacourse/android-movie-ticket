package woowacourse.movie.activity

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import woowacourse.movie.R
import woowacourse.movie.adapter.MovieAdapter
import woowacourse.movie.contract.MovieListContract
import woowacourse.movie.model.movie.Movie
import woowacourse.movie.presenter.MovieListPresenter

class MovieListActivity : AppCompatActivity(), MovieListContract.View {
    private lateinit var movieRecyclerView: RecyclerView
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_list_activity)
        val presenter =
            MovieListPresenter(
                view = this,
            )
        movieRecyclerView = findViewById(R.id.movies_list_item)
        presenter.loadMovies()
    }

    override fun displayMovies(movies: List<Movie>) {
        movieAdapter = MovieAdapter(movies, this)
        movieRecyclerView.adapter = movieAdapter
        movieRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun navigateToMovieDetail(movieId: Int) {
        val intent =
            Intent(this, MovieDetailActivity::class.java).apply {
                putExtra("MovieId", movieId)
            }
        startActivity(intent)
    }
}
