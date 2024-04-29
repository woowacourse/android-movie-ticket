package woowacourse.movie.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.adapter.MovieAdapter
import woowacourse.movie.detail.MovieDetailActivity
import woowacourse.movie.model.Movie

class HomeActivity : AppCompatActivity(), HomeContract.View {
    private val movieList: RecyclerView by lazy { findViewById(R.id.recycler_view_reservation_home) }
    private lateinit var homePresenter: HomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_home)

        homePresenter = HomePresenter(this)
    }

    override fun showMovies(movies: List<Movie>) {
        movieList.layoutManager = LinearLayoutManager(this)
        movieList.adapter =
            MovieAdapter(movies) { movieId ->
                homePresenter.deliverMovieId(movieId)
            }
    }

    override fun moveToReservationDetail(movieId: Int) {
        startActivity(MovieDetailActivity.getIntent(this, movieId))
    }
}
