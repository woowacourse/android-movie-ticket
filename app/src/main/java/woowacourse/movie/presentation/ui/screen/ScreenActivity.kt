package woowacourse.movie.presentation.ui.screen

import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.domain.model.ScreenView
import woowacourse.movie.domain.repository.DummyScreens
import woowacourse.movie.presentation.base.BaseActivity
import woowacourse.movie.presentation.ui.detail.DetailActivity
import woowacourse.movie.presentation.ui.screen.ScreenContract.Presenter
import woowacourse.movie.presentation.ui.screen.ScreenContract.View
import woowacourse.movie.presentation.ui.screen.adapter.ScreenRecyclerViewAdapter

class ScreenActivity : BaseActivity(), View {
    override val layoutResourceId: Int
        get() = R.layout.activity_screen
    override val presenter: Presenter by lazy { ScreenPresenter(this, DummyScreens()) }

    private val adapter: ScreenRecyclerViewAdapter by lazy { ScreenRecyclerViewAdapter(presenter) }
    private val rvScreen: RecyclerView by lazy { findViewById(R.id.rv_screen) }

    override fun initStartView() {
        initAdapter()
        presenter.loadScreens()
    }

    private fun initAdapter() {
        rvScreen.adapter = adapter
    }

    override fun showScreens(screens: List<ScreenView>) {
        adapter.updateScreens(screens)
    }

    override fun navigateToDetail(id: Int) {
        DetailActivity.startActivity(this, id)
    }
}
