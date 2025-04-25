package woowacourse.movie.view.movie

import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.view.booking.booking.BookingActivity

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

    override fun setMoveList(movies: List<Movie>) {
        val listView = findViewById<ListView>(R.id.list_view)

        listView.adapter =
            MovieAdapter(
                items = movies,
                onClickBooking = { movie ->
                    moveToBookingActivity(movie)
                },
            )
    }
}
