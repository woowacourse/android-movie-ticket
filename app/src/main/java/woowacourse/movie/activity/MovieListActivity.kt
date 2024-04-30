package woowacourse.movie.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.IntentKeys.MOVIE_ID
import woowacourse.movie.R
import woowacourse.movie.adapter.AdapterClickListenter
import woowacourse.movie.adapter.MovieAdapter
import woowacourse.movie.contract.MovieListContract
import woowacourse.movie.presenter.MovieListPresenter
import woowacourse.movie.uiModels.movie.MovieBrief

class MovieListActivity : AppCompatActivity(), MovieListContract.View, AdapterClickListenter {
    private lateinit var movieRecyclerView: RecyclerView
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var presenter: MovieListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_list_activity)
        presenter =
            MovieListPresenter(
                view = this,
            )
        movieRecyclerView = findViewById(R.id.movies_recycler_view)
        presenter.loadMovies()
    }

    override fun displayMovieBriefs(movieBriefs: List<MovieBrief>) {
        movieAdapter = MovieAdapter(movieBriefs, this)
        movieRecyclerView.adapter = movieAdapter
        movieRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun navigateToMovieDetail(movieId: Int) {
        val intent =
            Intent(this, MovieDetailActivity::class.java).apply {
                putExtra(MOVIE_ID, movieId)
            }
        startActivity(intent)
    }

    override fun onClick(position: Int) {
        navigateToMovieDetail(position)
    }
}
