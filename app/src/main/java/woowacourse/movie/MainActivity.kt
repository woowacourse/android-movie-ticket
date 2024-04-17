package woowacourse.movie

import android.content.Intent
import android.widget.ListView
import woowacourse.movie.model.Movie

class MainActivity : BaseActivity(), MainContract.View {
    private lateinit var adapter: MovieListAdapter
    private lateinit var presenter: MainContract.Presenter

    override fun getLayoutResId(): Int = R.layout.activity_main

    override fun onCreateSetup() {
        presenter = MainPresenterImpl(this)
        adapter = MovieListAdapter(this, presenter)
        showMovieList()
    }

    override fun showMovieList() {
        findViewById<ListView>(R.id.movieList).adapter = adapter
    }

    override fun moveToMovieDetail(movie: Movie) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra("posterImageId", movie.posterImageId)
        intent.putExtra("title", movie.title)
        intent.putExtra("screeningDate", movie.screeningDate)
        intent.putExtra("runningTime", movie.runningTime)
        intent.putExtra("summary", movie.summary)
        startActivity(intent)
    }
}
