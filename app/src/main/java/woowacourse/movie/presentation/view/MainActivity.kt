package woowacourse.movie.presentation.view

import android.content.Intent
import android.widget.ListView
import woowacourse.movie.R
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.presentation.adapter.MovieListAdapter
import woowacourse.movie.presentation.base.BaseActivity
import woowacourse.movie.presentation.contract.MainContract
import woowacourse.movie.presentation.presenter.MainPresenterImpl

class MainActivity : BaseActivity(), MainContract.View {
    private lateinit var adapter: MovieListAdapter
    private lateinit var presenter: MainContract.Presenter

    override fun getLayoutResId(): Int = R.layout.activity_main

    override fun onCreateSetup() {
        presenter = MainPresenterImpl(this)
        adapter = MovieListAdapter(presenter)
        showMovieList()
    }

    override fun showMovieList() {
        findViewById<ListView>(R.id.movieList).adapter = adapter
    }

    override fun moveToMovieDetail(movie: Movie) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra(MovieDetailActivity.INTENT_POSTER_IMAGE_ID, movie.posterImageId)
        intent.putExtra(MovieDetailActivity.INTENT_TITLE, movie.title)
        intent.putExtra(MovieDetailActivity.INTENT_SCREENING_DATE, movie.screeningDate)
        intent.putExtra(MovieDetailActivity.INTENT_RUNNING_TIME, movie.runningTime)
        intent.putExtra(MovieDetailActivity.INTENT_SUMMARY, movie.summary)
        startActivity(intent)
    }
}
