package woowacourse.movie.feature.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.feature.home.list.MovieListAdapter
import woowacourse.movie.feature.home.ui.MovieUiModel
import woowacourse.movie.feature.home.ui.toMovieListUiModels
import woowacourse.movie.feature.reservation.MovieReservationActivity
import woowacourse.movie.model.data.MovieRepositoryImpl
import woowacourse.movie.model.data.dto.Movie
import woowacourse.movie.utils.BaseActivity

class MovieHomeActivity : BaseActivity<MovieHomeContract.Presenter>(), MovieHomeContract.View {
    private val movieList: RecyclerView by lazy { findViewById(R.id.movie_list) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_home)

        presenter.loadMovieData()
    }

    override fun initializePresenter() = MovieHomePresenter(this, MovieRepositoryImpl)

    override fun setUpMovieList(movies: List<Movie>) {
        val movies = movies.toMovieListUiModels(this, R.drawable.img_advertisement, ADVERTISEMENT_INTERVAL)
        movieList.adapter =
            MovieListAdapter(movies) { _, position ->
                MovieReservationActivity.startActivity(
                    this@MovieHomeActivity,
                    (movies[position] as MovieUiModel).id,
                )
            }
    }

    companion object {
        private val TAG = MovieHomeActivity::class.simpleName
        private const val ADVERTISEMENT_INTERVAL = 3

        fun startActivity(context: Context) {
            context.startActivity(Intent(context, MovieHomeActivity::class.java))
        }
    }
}
