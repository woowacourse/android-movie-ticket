package woowacourse.movie.view.movies

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.data.MovieStore
import woowacourse.movie.domain.model.movies.Movie
import woowacourse.movie.view.booking.BookingActivity
import woowacourse.movie.view.movies.adapter.MovieAdapter
import woowacourse.movie.view.movies.model.UiModel

class MovieListActivity : AppCompatActivity(), MovieListContract.View {
    private val presenter: MovieListContract.Presenter by lazy {
        MovieListPresenter(this, MovieStore())
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
        presenter.loadUiData()
    }

    override fun showMovieList(movieList: List<UiModel>) {
        val rv = findViewById<RecyclerView>(R.id.rv)
        val adapter =
            MovieAdapter(
                itemsList = movieList,
                onClickBooking = { moveToBookingComplete(it) },
            )
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter
    }

    private fun moveToBookingComplete(movie: Movie) {
        startActivity(BookingActivity.newIntent(this, movie))
    }
}
