package woowacourse.movie.movieList

import MovieListView
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R

class MovieListActivity : AppCompatActivity(), MovieListView {
    private lateinit var moviesListView: ListView
    private lateinit var presenter: MovieListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mainactivity)
        presenter = MovieListPresenter(this)
        initAdapter()
    }

    override fun getContext(): Context = this

    override fun startActivity(intent: Intent) {
        super.startActivity(intent)
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun initAdapter() {
        moviesListView = findViewById(R.id.movies_list_item)
        moviesListView.adapter = MovieAdapter(this, presenter.theaters, presenter)
    }
}
