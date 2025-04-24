package woowacourse.movie.view.movie

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.view.movie.model.MovieUiModel
import woowacourse.movie.view.reservation.MovieReservationActivity

class MovieListActivity :
    AppCompatActivity(),
    MovieListContract.View {
    private lateinit var presenter: MovieListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_movie_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        presenter = MovieListPresenter(this)
        presenter.loadMovieListScreen()
    }

    override fun showMovieList(movies: List<MovieUiModel>) {
        val movieListView: ListView = findViewById(R.id.movie_list)
        val movieAdapter =
            MovieAdapter(movies) { movie ->
                startActivity(MovieReservationActivity.newIntent(this, movie))
            }
        movieListView.adapter = movieAdapter
    }

    companion object {
        fun newIntent(context: Context): Intent = Intent(context, MovieListActivity::class.java)
    }
}
