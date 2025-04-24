package woowacourse.movie

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.BookingActivity.Companion.KEY_MOVIE_DATA
import woowacourse.movie.model.Movie
import woowacourse.movie.model.MovieContract
import woowacourse.movie.model.adapter.MovieAdapter
import woowacourse.movie.presenter.MoviePresenter

class MovieActivity : AppCompatActivity(), MovieContract.View {
    private lateinit var presenter: MovieContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        setUpUi()

        presenter = MoviePresenter(this)
        presenter.initializeData(intent)
    }

    private fun setUpUi() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun showMovies(movies: List<Movie>) {
        val adapter =
            MovieAdapter(resources, movies) { movie ->
                presenter.onReserveClicked(movie)
            }
        findViewById<ListView>(R.id.listview_layout).adapter = adapter
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun startBookingActivity(movie: Movie) {
        val intent =
            Intent(this, BookingActivity::class.java).apply {
                putExtra(KEY_MOVIE_DATA, movie)
            }
        startActivity(intent)
    }
}
