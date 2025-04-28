package woowacourse.movie.activity.movielist

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.activity.booking.BookingActivity
import woowacourse.movie.adapter.MovieListAdapter
import woowacourse.movie.domain.Movie

class MovieListActivity :
    AppCompatActivity(),
    MovieListContract.View {
    private lateinit var presenter: MovieListContract.Presenter
    private lateinit var adapter: MovieListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        presenter = MovieListPresenter(this)

        val movieList: RecyclerView = findViewById(R.id.movie_list)
        adapter =
            MovieListAdapter(emptyList()) { movie ->
                presenter.onMovieClicked(movie)
            }
        movieList.adapter = adapter

        presenter.loadMovies()
    }

    override fun showMovieList(movies: List<Movie>) {
        adapter.setItems(movies)
    }

    override fun moveToBooking(movie: Movie) {
        val intent =
            Intent(this, BookingActivity::class.java).apply {
                putExtra("MOVIE_INFO", movie)
            }
        startActivity(intent)
    }
}
