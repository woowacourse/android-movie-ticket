package woowacourse.movie.movieList

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R

class MovieListActivity : AppCompatActivity() {
    private lateinit var moviesListView: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mainactivity)
        initAdapter()
    }

    private fun initAdapter() {
        val presenter = MovieListPresenter(this)
        moviesListView = findViewById(R.id.movies_list_item)
        moviesListView.adapter = presenter.movieAdapter
    }
}
