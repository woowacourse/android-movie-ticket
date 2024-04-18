package woowacourse.movie.view

import android.os.Bundle
import android.widget.ListView
import woowacourse.movie.R
import woowacourse.movie.conrtract.MainContract
import woowacourse.movie.presenter.MainPresenter

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
