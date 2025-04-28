package woowacourse.movie.ui.movielist.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.MovieListItem
import woowacourse.movie.ui.booking.view.BookingActivity
import woowacourse.movie.ui.movielist.contract.MovieListContract
import woowacourse.movie.ui.movielist.presenter.MovieListPresenter

class MovieListActivity :
    AppCompatActivity(),
    MovieListContract.View {
    val movieListPresenter = MovieListPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_movie_list)
        applyWindowInsets()

        movieListPresenter.updateMovieList()
    }

    private fun applyWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun moveToBookingActivity(movie: Movie) {
        startActivity(BookingActivity.newIntent(this, movie))
    }

    override fun setMoveListItems(items: List<MovieListItem>) {
        val recyclerView = findViewById<RecyclerView>(R.id.movies_recycler_view)
        val adapter =
            MovieAdapter(
                onClickBooking = { movie ->
                    moveToBookingActivity(movie)
                },
            )

        recyclerView.adapter = adapter
        adapter.submitList(items)
    }
}
