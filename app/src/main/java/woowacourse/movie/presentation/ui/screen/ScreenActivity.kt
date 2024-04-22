package woowacourse.movie.presentation.ui.screen

import android.widget.ListView
import woowacourse.movie.R
import woowacourse.movie.domain.model.Screen
import woowacourse.movie.domain.repository.DummyScreens
import woowacourse.movie.presentation.base.BaseActivity
import woowacourse.movie.presentation.ui.detail.DetailActivity
import woowacourse.movie.presentation.ui.screen.ScreenContract.Presenter
import woowacourse.movie.presentation.ui.screen.ScreenContract.View
import woowacourse.movie.presentation.ui.screen.adapter.ScreenAdapter

class ScreenActivity : BaseActivity(), View {
    override val layoutResourceId: Int
        get() = R.layout.activity_main
    override val presenter: Presenter by lazy { ScreenPresenter(this, DummyScreens()) }

    private val adapter: ScreenAdapter by lazy { ScreenAdapter(presenter) }
    private val lvScreen: ListView by lazy { findViewById(R.id.lv_screen) }

    override fun initStartView() {
        initAdapter()
        presenter.loadScreens()
    }

    private fun initAdapter() {
        lvScreen.adapter = adapter
    }

    override fun showScreens(screens: List<Screen>) {
        adapter.updateScreens(screens)
    }

    override fun navigateToDetail(id: Int) {
        DetailActivity.startActivity(this, id)
    }
}
