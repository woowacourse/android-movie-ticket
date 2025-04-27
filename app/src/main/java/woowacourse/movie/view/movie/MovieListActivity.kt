package woowacourse.movie.view.movie

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.view.movie.adapter.MovieAdapter
import woowacourse.movie.view.movie.adapter.MovieListItem
import woowacourse.movie.view.reservation.MovieReservationActivity

class MovieListActivity :
    AppCompatActivity(),
    MovieListContract.View {
    private lateinit var presenter: MovieListPresenter
    private val movieListView: RecyclerView by lazy { findViewById(R.id.movie_list) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpView()

        presenter = MovieListPresenter(this)
        presenter.loadMovieListScreen()
    }

    override fun showMovieList(movies: List<MovieListItem>) {
        val movieAdapter =
            MovieAdapter(movies) { position ->
                when (val item = movies[position]) {
                    is MovieListItem.MovieItem ->
                        startActivity(MovieReservationActivity.newIntent(this, item.movie))

                    is MovieListItem.AdItem -> Unit
                }
            }
        movieListView.adapter = movieAdapter
    }

    private fun setUpView() {
        enableEdgeToEdge()
        setContentView(R.layout.activity_movie_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    companion object {
        fun newIntent(context: Context): Intent = Intent(context, MovieListActivity::class.java)
    }
}
