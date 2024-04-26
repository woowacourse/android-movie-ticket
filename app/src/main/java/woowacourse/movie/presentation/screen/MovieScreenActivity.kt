package woowacourse.movie.presentation.screen

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.data.AdRepositoryImpl
import woowacourse.movie.data.MovieRepositoryImpl
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.presentation.reservation.MovieReservationActivity
import woowacourse.movie.presentation.screen.adapter.MovieScreenAdapter

class MovieScreenActivity : AppCompatActivity(), MovieScreenContract.View {
    private lateinit var movieAdapter: MovieScreenAdapter
    private lateinit var movieRecyclerView: RecyclerView
    private val presenter =
        MovieScreenPresenter(
            view = this@MovieScreenActivity,
            movieRepository = MovieRepositoryImpl(),
            adRepository = AdRepositoryImpl(),
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_screen)
        initView()
    }


    private fun initView() {
        movieRecyclerView = findViewById(R.id.movie_recycler_view)
        presenter.requestAd { ad ->
            movieAdapter =
                MovieScreenAdapter(
                    context = this@MovieScreenActivity,
                    ad = ad,
                    onMovieSelected = { movieId ->
                        moveToReservation(movieId)
                    },
                )
        }
        movieRecyclerView.adapter = movieAdapter
        movieRecyclerView.layoutManager = LinearLayoutManager(this@MovieScreenActivity)
        presenter.loadScreenMovies()
    }

    override fun showScreenMovies(movies: List<Movie>) {
        movieAdapter.updateMovies(movies)
    }

    override fun moveToReservation(movieId: Int) {
        val intent = Intent(this, MovieReservationActivity::class.java)
        intent.putExtra(MovieScreenPresenter.KEY_NAME_MOVIE, movieId)
        this.startActivity(intent)
    }
}
