package woowacourse.movie.home

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.adapter.MovieCatalogAdapter
import woowacourse.movie.detail.MovieDetailActivity
import woowacourse.movie.model.Movie

class HomeActivity : AppCompatActivity(), HomeContract.View {
    private val movieList: ListView by lazy { findViewById(R.id.list_view_reservation_home) }
    private lateinit var homePresenter: HomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_home)

        homePresenter = HomePresenter(this)
        homePresenter.loadMovies()
    }

    override fun showMovies(movies: List<Movie>) {
        movieList.adapter =
            MovieCatalogAdapter(this, movies) { movieId ->
                homePresenter.deliverMovie(movieId)
            }
    }

    override fun moveToReservationDetail(movieId: Int) {
        startActivity(MovieDetailActivity.getIntent(this, movieId))
    }
}
