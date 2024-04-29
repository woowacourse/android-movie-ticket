package woowacourse.movie.presentation.ui.main

import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.data.repository.MovieRepositoryImpl
import woowacourse.movie.presentation.base.BaseActivity
import woowacourse.movie.presentation.ui.detail.MovieDetailActivity
import woowacourse.movie.presentation.ui.main.adapter.MovieListAdapter
import woowacourse.movie.presentation.uimodel.MovieUiModel

class MainActivity : BaseActivity(), MainContract.View {
    private lateinit var adapter: MovieListAdapter
    private lateinit var presenter: MainContract.Presenter

    override fun getLayoutResId(): Int = R.layout.activity_main

    override fun onCreateSetup() {
        presenter = MainPresenter(this, MovieRepositoryImpl)
    }
    
    override fun showMovieList(movieList: List<MovieUiModel>) {
        val recyclerView = findViewById<RecyclerView>(R.id.movieList).apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
        
        adapter = MovieListAdapter(movieList) { movieId ->
            presenter.requestMovieDetail(movieId)
        }
        
        recyclerView.adapter = adapter
    }

    override fun moveToMovieDetail(movieId: Int) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra(EXTRA_MOVIE_ID, movieId)
        startActivity(intent)
    }

    override fun showMessage(message: String) {
        showSnackBar(message)
    }

    companion object {
        const val EXTRA_MOVIE_ID = "movieId"
    }
}
