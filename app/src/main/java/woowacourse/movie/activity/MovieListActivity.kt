package woowacourse.movie.activity

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.presenter.MovieListActivityPresenter

class MovieListActivity : AppCompatActivity() {
    private lateinit var moviesListView: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_list_activity)
        val presenter = MovieListActivityPresenter(this)
        moviesListView = findViewById(R.id.movies_list_item)
        moviesListView.adapter = presenter.movieAdapter
    }
}
