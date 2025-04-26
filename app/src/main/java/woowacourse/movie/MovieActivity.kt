package woowacourse.movie

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.BookingDetailActivity.Companion.KEY_MOVIE_DATA
import woowacourse.movie.movie.MovieContract
import woowacourse.movie.movie.MoviePresenter
import woowacourse.movie.movie.MovieUiModel
import woowacourse.movie.movie.adapter.MovieAdapter

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

    override fun showMovies(movies: List<MovieUiModel>) {
        val adapter =
            MovieAdapter(this, movies) { movie ->
                presenter.onReserveClicked(movie)
            }
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView_layout)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun startBookingActivity(movie: MovieUiModel) {
        val intent =
            Intent(this, BookingDetailActivity::class.java).apply {
                putExtra(KEY_MOVIE_DATA, movie)
            }
        startActivity(intent)
    }
}
