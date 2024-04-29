package woowacourse.movie.presentation.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.domain.admodel.Ad
import woowacourse.movie.presentation.adapter.MovieListAdapter
import woowacourse.movie.presentation.base.BaseActivity
import woowacourse.movie.presentation.contract.MainContract
import woowacourse.movie.presentation.presenter.MainPresenterImpl
import woowacourse.movie.presentation.uimodel.MovieUiModel

class MainActivity : BaseActivity(), MainContract.View, MainContract.ViewActions {
    private lateinit var adapter: MovieListAdapter
    private lateinit var presenter: MainContract.Presenter

    override fun getLayoutResId(): Int = R.layout.activity_main

    override fun onCreateSetup(savedInstanceState: Bundle?) {
        adapter = MovieListAdapter(emptyList(), emptyList(), this)
        presenter = MainPresenterImpl()
        presenter.attachView(this)
        showMovieList()
    }

    override fun onUpdateMovies(movies: List<MovieUiModel>) {
        adapter.updateMovieList(movies)
    }

    override fun onUpdateAds(ads: List<Ad>) {
        adapter.updateAdsList(ads)
    }

    override fun showMovieList() {
        val movieListView = findViewById<RecyclerView>(R.id.movieList)
        movieListView.layoutManager = LinearLayoutManager(this)
        movieListView.adapter = adapter
    }

    override fun reserveMovie(movieId: Int) {
        presenter.onReserveButtonClicked(movieId)
    }

    override fun showAdContent(content: String) {
        runOnUiThread {
            Toast.makeText(this, content, Toast.LENGTH_SHORT).show()
        }
    }

    override fun moveToMovieDetail(movieId: Int) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra(MovieDetailActivity.INTENT_MOVIE_ID, movieId)
        startActivity(intent)
    }
}
