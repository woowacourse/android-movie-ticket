package woowacourse.movie.movieList

import MovieAdapter
import MovieListView
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.model.MovieDisplayData
import woowacourse.movie.model.theater.Theater
import woowacourse.movie.movieDetail.MovieDetailActivity

class MovieListActivity : AppCompatActivity(), MovieListView {
    private lateinit var moviesRecyclerView: RecyclerView
    private lateinit var presenter: MovieListPresenter
    private lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_list)
        initializeViews()
        initAdapter()
    }

    private fun initializeViews() {
        moviesRecyclerView = findViewById(R.id.movies_list)
        moviesRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MovieAdapter(this, mutableListOf()) { position ->
            presenter.onDetailButtonClicked(position)
        }
        moviesRecyclerView.adapter = adapter
    }

    private fun initAdapter() {
        presenter = MovieListPresenter(this)
        presenter.loadMovies()
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun updateAdapter(displayData: List<MovieDisplayData>) {
        adapter.updateItems(displayData)
    }

    override fun navigateToMovieDetail(theater: Theater) {
        val intent = Intent(this, MovieDetailActivity::class.java).apply {
            putExtra("Theater", theater)
        }
        startActivity(intent)
    }
}
