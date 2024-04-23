package woowacourse.movie.feature.home

import android.os.Bundle
import android.widget.ListView
import woowacourse.movie.R
import woowacourse.movie.feature.home.list.MovieContentListAdapter
import woowacourse.movie.feature.home.ui.toUiModelList
import woowacourse.movie.feature.reservation.MovieReservationActivity
import woowacourse.movie.model.data.MovieContentsImpl
import woowacourse.movie.model.data.dto.MovieContent
import woowacourse.movie.utils.BaseActivity

class MovieHomeActivity : BaseActivity<MovieHomeContract.Presenter>(), MovieHomeContract.View {
    private val movieContentList: ListView by lazy { findViewById(R.id.movie_content_list) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_home)

        presenter.loadMovieData()
    }

    override fun initializePresenter() = MovieHomePresenter(this, MovieContentsImpl)

    override fun setUpMovieContentList(movieContents: List<MovieContent>) {
        val movies = movieContents.toUiModelList(this)
        movieContentList.adapter =
            MovieContentListAdapter(movies) { _, movieContentId ->
                MovieReservationActivity.startActivity(this@MovieHomeActivity, movieContentId)
            }
    }
}
