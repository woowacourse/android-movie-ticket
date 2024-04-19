package woowacourse.movie.activity

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.adapter.MovieAdapter
import woowacourse.movie.contract.MovieListContract
import woowacourse.movie.model.theater.Theater
import woowacourse.movie.presenter.MovieListPresenter

class MovieListActivity : AppCompatActivity(), MovieListContract.View {
    private lateinit var moviesListView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_list_activity)
        val movieAdapter = MovieAdapter()
        MovieListPresenter(
            movieListView = this,
            movieAdapter = movieAdapter,
        )
        moviesListView = findViewById(R.id.movies_list_item)
        moviesListView.adapter = movieAdapter
    }

    override fun navigateToMovieDetail(theater: Theater) {
        val intent =
            Intent(this, MovieDetailActivity::class.java).apply {
                putExtra("Theater", theater)
            }
        startActivity(intent)
    }
}
