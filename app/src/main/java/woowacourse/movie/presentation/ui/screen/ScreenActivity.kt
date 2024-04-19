package woowacourse.movie.presentation.ui.screen

import android.widget.ListView
import woowacourse.movie.R
import woowacourse.movie.domain.model.Screen
import woowacourse.movie.domain.repository.DummyScreens
import woowacourse.movie.presentation.base.BaseActivity
import woowacourse.movie.presentation.ui.screen.ScreenContract.Presenter
import woowacourse.movie.presentation.ui.screen.ScreenContract.View
import woowacourse.movie.presentation.ui.screen.adapter.ScreenAdapter

class ScreenActivity : BaseActivity(), View {
    override val layoutResourceId: Int
        get() = R.layout.activity_main
    override val presenter: Presenter by lazy { ScreenPresenter(this, DummyScreens()) }

    private lateinit var adapter: ScreenAdapter
    private lateinit var lvScreen: ListView

    override fun initStartView() {
        lvScreen = findViewById(R.id.lv_screen)
    }

    override fun initBinding() {
        presenter.loadScreens()
    }

    override fun showScreens(screens: List<Screen>) {
        adapter = ScreenAdapter(screens)
        lvScreen.adapter = adapter
    }
}
