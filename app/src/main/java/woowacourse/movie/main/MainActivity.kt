package woowacourse.movie.main

import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.MoviesAdapter
import woowacourse.movie.R
import woowacourse.movie.ReserveActivity
import woowacourse.movie.domain.Movie

class MainActivity : AppCompatActivity(), MainContract.View {
    private val moviesView: ListView by lazy { findViewById(R.id.lv_movies) }
    private val presenter: MainPresenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        presenter.initMovies()
    }

    override fun showMovies(movies: List<Movie>) {
        moviesView.adapter =
            MoviesAdapter(movies) { movie ->
                val intent = ReserveActivity.newIntent(this, movie)
                startActivity(intent)
            }
    }
}
