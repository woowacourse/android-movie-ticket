package woowacourse.movie.presentation.movie_list

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.presentation.IntentKeys.MOVIE_ID
import woowacourse.movie.presentation.movie_detail.MovieDetailActivity
import woowacourse.movie.R
import woowacourse.movie.presentation.movie_list.adapter.AdapterClickListenter
import woowacourse.movie.presentation.movie_list.adapter.MovieAdapter
import woowacourse.movie.uimodel.movie.MovieBrief

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
