package woowacourse.movie.movieList

import MovieListView
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.model.MovieDisplayData

class MovieListActivity : AppCompatActivity(), MovieListView {
    private lateinit var moviesListView: ListView
    private lateinit var presenter: MovieListPresenter
    private lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_list)
        presenter = MovieListPresenter(this)
        moviesListView = findViewById(R.id.movies_list_item)
        initAdapter()
    }

    private fun initAdapter() {
        adapter = MovieAdapter(this, mutableListOf(), presenter)
        moviesListView.adapter = adapter
        presenter.loadMovies()
    }

    override fun getContext(): Context = this

    override fun startActivity(intent: Intent) {
        super.startActivity(intent)
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun updateAdapter(displayData: List<MovieDisplayData>) {
        adapter.clear()
        adapter.addAll(displayData)
        adapter.notifyDataSetChanged()
    }

}
