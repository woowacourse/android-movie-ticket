package woowacourse.movie.ui.screen

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.domain.repository.DummyMovies
import woowacourse.movie.domain.repository.DummyScreens
import woowacourse.movie.ui.ScreenPreviewUI
import woowacourse.movie.ui.detail.ScreenDetailActivity
import woowacourse.movie.ui.screen.adapter.ScreenAdapter2

class ScreenActivity : AppCompatActivity(), ScreenContract2.View {
    private val listView: ListView by lazy { findViewById(R.id.lv_screen) }

    private lateinit var adapter: ScreenAdapter2
    private val screenPresenter: ScreenContract2.Presenter by lazy {
        ScreenPresenter2(
            this,
            DummyMovies(),
            DummyScreens(),
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initAdapter()
        screenPresenter.loadScreens()
    }

    private fun initAdapter() {
        adapter =
            ScreenAdapter2(emptyList()) { screenId ->
                ScreenDetailActivity.startActivity(this, screenId)
            }
        listView.adapter = adapter
    }

    override fun showScreens(screens: List<ScreenPreviewUI>) {
        adapter.updateScreens(screens)
    }
}
