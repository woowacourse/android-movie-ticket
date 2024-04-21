package woowacourse.movie.ui.main

import android.os.Bundle
import android.widget.ListView
import woowacourse.movie.R
import woowacourse.movie.ui.adapter.MovieContentListAdapter
import woowacourse.movie.ui.base.BaseActivity

class MainActivity : BaseActivity<MainContract.Presenter>(), MainContract.View {
    private val presenter: MainContract.Presenter by lazy { initializePresenter() }
    private val movieContentList: ListView by lazy { findViewById(R.id.movie_content_list) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.saveMovieContent()
        setUpMovieContentListAdapter()
    }

    override fun initializePresenter() = MainPresenter()

    override fun setUpMovieContentListAdapter() {
        movieContentList.adapter = MovieContentListAdapter(this)
    }
}
