package woowacourse.movie.presentation.ui.main

import android.content.Intent
import android.widget.ListView
import woowacourse.movie.R
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.presentation.base.BaseActivity
import woowacourse.movie.presentation.ui.detail.MovieDetailActivity
import woowacourse.movie.presentation.ui.main.adapter.MovieListAdapter

class MainActivity : BaseActivity(), MainContract.View {
    private lateinit var adapter: MovieListAdapter
    private lateinit var presenter: MainContract.Presenter

    override fun getLayoutResId(): Int = R.layout.activity_main

    override fun onCreateSetup() {
        presenter = MainPresenterImpl(this)
        presenter.loadMovieList()
    }

    override fun showMovieList(movieList: List<Movie>) {
        adapter =
            MovieListAdapter(movieList) { movie ->
                presenter.requestMovieDetail(movie)
            }
        findViewById<ListView>(R.id.movieList).adapter = adapter
    }

    override fun moveToMovieDetail(movie: Movie) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra(EXTRA_POSTER_IMAGE_SRC, movie.posterSrc)
        intent.putExtra(EXTRA_TITLE, movie.title)
        intent.putExtra(EXTRA_SCREENING_DATE, movie.screeningDateToString())
        intent.putExtra(EXTRA_RUNNING_TIME, movie.runningTime)
        intent.putExtra(EXTRA_SUMMARY, movie.summary)
        startActivity(intent)
    }

    override fun showMessage(message: String) {
        showSnackBar(message)
    }

    companion object {
        const val EXTRA_POSTER_IMAGE_SRC = "posterSrc"
        const val EXTRA_TITLE = "title"
        const val EXTRA_SCREENING_DATE = "screeningDate"
        const val EXTRA_RUNNING_TIME = "runningTime"
        const val EXTRA_SUMMARY = "summary"
    }
}
