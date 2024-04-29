package woowacourse.movie.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.model.data.MovieContentsImpl
import woowacourse.movie.model.movie.MovieContent
import woowacourse.movie.ui.base.BaseActivity
import woowacourse.movie.ui.home.adapter.MovieContentAdapter
import woowacourse.movie.ui.reservation.MovieReservationActivity

class MovieHomeActivity : BaseActivity<MovieHomeContract.Presenter>(), MovieHomeContract.View {
    private val movieContentList: RecyclerView by lazy { findViewById(R.id.movie_content_list) }

    override fun initializePresenter() = MovieHomePresenter(this, MovieContentsImpl)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_home)

        presenter.loadMovieContents()
    }

    override fun showMovieContents(movieContents: List<MovieContent>) {
        movieContentList.adapter =
            MovieContentAdapter(movieContents) { view, id ->
                Intent(view.context, MovieReservationActivity::class.java).run {
                    putExtra(MovieHomeKey.ID, id)
                    ContextCompat.startActivity(view.context, this, null)
                }
            }
    }
}
