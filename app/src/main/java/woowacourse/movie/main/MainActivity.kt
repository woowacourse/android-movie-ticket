package woowacourse.movie.main

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.MoviesAdapter
import woowacourse.movie.R
import woowacourse.movie.domain.Movie
import woowacourse.movie.reserve.ReserveActivity

class MainActivity : AppCompatActivity(), MainContract.View {
    private val moviesView: RecyclerView by lazy { findViewById(R.id.rv_movies) }
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
        moviesView.layoutManager = LinearLayoutManager(this)
        moviesView.adapter =
            MoviesAdapter(movies) { movie ->
                val intent = ReserveActivity.newIntent(this, movie)
                startActivity(intent)
            }
    }
}
