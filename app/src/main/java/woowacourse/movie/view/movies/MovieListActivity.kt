package woowacourse.movie.view.movies

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.view.booking.BookingActivity
import woowacourse.movie.view.movies.MovieListContract.PresenterFactory
import woowacourse.movie.view.movies.adapter.MovieAdapter

class MovieListActivity : AppCompatActivity(), MovieListContract.View {
    private val presenter: MovieListContract.Presenter by lazy {
        PresenterFactory.providePresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_movie_list)
        initView()
    }

    private fun initView() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        presenter.setMovies()
    }

    override fun showMovieList(movieList: MovieListContract.MovieModel) {
        val listView = findViewById<ListView>(R.id.list_view)
        val adapter =
            MovieAdapter(
                itemsList = movieList,
                onClickBooking = ::moveToBookingComplete,
            )

        listView.adapter = adapter
    }

    override fun moveToBookingComplete(movieIdx: Int) {
        val intent =
            Intent(this, BookingActivity::class.java).apply {
                putExtra(KEY_MOVIE, movieIdx)
            }
        startActivity(intent)
    }

    companion object {
        const val KEY_MOVIE = "MOVIE"
    }
}
