package woowacourse.movie.presentation.screen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.data.MovieRepositoryImpl
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.presentation.reservation.MovieReservationActivity
import woowacourse.movie.presentation.screen.adapter.MovieScreenAdapter

class MovieScreenActivity : AppCompatActivity(), MovieScreenContract.View {
    private lateinit var movieAdapter: MovieScreenAdapter
    private lateinit var movieListView: ListView
    private val presenter =
        MovieScreenPresenter(
            view = this@MovieScreenActivity,
            movieRepository = MovieRepositoryImpl(),
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_screen)
        initView()
    }

    private fun initView() {
        movieListView = findViewById(R.id.movie_list_view)
        presenter.loadScreenMovies()
    }

    override fun showScreenMovies(movies: List<Movie>) {
        movieAdapter =
            MovieScreenAdapter(
                context = this@MovieScreenActivity,
                movies = movies,
                onMovieSelected = { movieId ->
                    moveToReservation(movieId)
                },
            )
        movieListView.adapter = movieAdapter
    }

    override fun moveToReservation(movieId: Int) {
        val intent = Intent(this, MovieReservationActivity::class.java)
        intent.putExtra(MovieScreenPresenter.KEY_NAME_MOVIE, movieId)
        this.startActivity(intent)
    }
}
