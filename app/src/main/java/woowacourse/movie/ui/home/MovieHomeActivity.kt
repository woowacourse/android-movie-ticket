package woowacourse.movie.ui.home

import android.os.Bundle
import android.widget.ListView
import woowacourse.movie.R
import woowacourse.movie.model.data.MovieContentsImpl
import woowacourse.movie.model.movie.MovieContent
import woowacourse.movie.ui.base.BaseActivity
import woowacourse.movie.ui.home.adapter.MovieContentListAdapter

class MovieHomeActivity : BaseActivity<MovieHomeContract.Presenter>(), MovieHomeContract.View {
    private val presenter: MovieHomeContract.Presenter by lazy { initializePresenter() }
    private val movieContentList: ListView by lazy { findViewById(R.id.movie_content_list) }

    override fun initializePresenter() = MovieHomePresenter(this, MovieContentsImpl)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_home)

        presenter.setMovieContents()
    }

    override fun setMovieContentsUi(movieContents: List<MovieContent>) {
        movieContentList.adapter = MovieContentListAdapter(this, movieContents)
    }
}
