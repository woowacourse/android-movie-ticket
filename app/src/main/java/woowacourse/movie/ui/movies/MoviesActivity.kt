package woowacourse.movie.ui.movies

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.domain.movies.Movie
import woowacourse.movie.ui.BaseActivity
import woowacourse.movie.ui.booking.BookingActivity

class MoviesActivity :
    BaseActivity(),
    MovieContract.View {
    private lateinit var presenter: MoviePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupScreen(R.layout.activity_main)

        presenter = MoviePresenter(this)
        presenter.loadAllMovies()
    }

    override fun showAllMovies(movies: List<Movie>) {
        val adapter =
            MovieAdapter(
                movies,
                onReservationClickListener =
                    { movieId ->
                        val intent = Intent(this, BookingActivity::class.java)
                        intent.putExtra(getString(R.string.movie_info_key), movieId)
                        startActivity(intent)
                    },
            )
        val recyclerView = findViewById<RecyclerView>(R.id.movies)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}
