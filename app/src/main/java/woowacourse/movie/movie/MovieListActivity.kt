package woowacourse.movie.movie

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.movie.adapter.MoviesAdapter
import woowacourse.movie.reserve.ReservationActivity

class MovieListActivity : AppCompatActivity(), MovieListContract.View {
    private val moviesView: RecyclerView by lazy { findViewById(R.id.rv_movies) }
    private val presenter: MovieListPresenter = MovieListPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_movie_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        presenter.initMovies()
    }

    override fun showMovies(movies: List<MovieListItem>) {
        moviesView.layoutManager = LinearLayoutManager(this)
        moviesView.adapter =
            MoviesAdapter(movies) { movie ->
                val intent = ReservationActivity.newIntent(this, movie)
                startActivity(intent)
            }
    }
}
