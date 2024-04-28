package woowacourse.movie.movieList

import MovieListView
import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.model.MovieDisplayData
import woowacourse.movie.model.theater.Theater
import woowacourse.movie.movieDetail.MovieDetailActivity

class MovieListActivity : AppCompatActivity(), MovieListView {
    private val moviesListView: ListView by lazy { findViewById(R.id.movies_list_item) }
    private val presenter: MovieListPresenter by lazy { MovieListPresenter(this) }
    private val adapter: MovieAdapter by lazy {
        MovieAdapter(this, mutableListOf()) { position ->
            presenter.onDetailButtonClicked(position)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_list)
        initAdapter()
    }

    private fun initAdapter() {
        moviesListView.adapter = adapter
        presenter.loadMovies()
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun updateAdapter(displayData: List<MovieDisplayData>) {
        adapter.updateItems(items = displayData)
    }

    override fun navigateToMovieDetail(theater: Theater) {
        val intent = Intent(this, MovieDetailActivity::class.java).apply {
            putExtra("Theater", theater)
        }
        startActivity(intent)
    }
}
