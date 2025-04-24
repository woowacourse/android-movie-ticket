package woowacourse.movie.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.ui.main.adapter.MoviesAdapter
import woowacourse.movie.ui.reserve.ReserveActivity
import woowacourse.movie.ui.reserve.ReserveActivity.Companion.KEY_RESERVE_ACTIVITY_MOVIE

class MainActivity : AppCompatActivity(), MainContract.View {
    private val moviesView: ListView by lazy { findViewById(R.id.lv_movies) }
    private val presenter: MainContract.Presenter by lazy { MainPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        initSystemUI()
        presenter.showMovies()
    }

    private fun initSystemUI() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun showMovies(movies: List<Movie>) {
        initMoviesView(movies)
    }

    private fun initMoviesView(movies: List<Movie>) {
        val adapter =
            MoviesAdapter(movies) { movie ->
                val intent = Intent(this, ReserveActivity::class.java)
                intent.putExtra(KEY_RESERVE_ACTIVITY_MOVIE, movie)
                startActivity(intent)
            }
        moviesView.adapter = adapter
    }
}
