package woowacourse.movie.feature.home

import android.os.Bundle
import android.widget.ListView
import woowacourse.movie.R
import woowacourse.movie.base.BaseActivity
import woowacourse.movie.feature.home.list.MovieContentListAdapter

class MainActivity : BaseActivity<MainContract.Presenter>(), MainContract.View {
    private val movieContentList: ListView by lazy { findViewById(R.id.movie_content_list) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpMovieContentListAdapter()
    }

    override fun initializePresenter() = MainPresenter()

    override fun setUpMovieContentListAdapter() {
        movieContentList.adapter = MovieContentListAdapter(this)
    }
}
