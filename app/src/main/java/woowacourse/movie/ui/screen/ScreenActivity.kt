package woowacourse.movie.ui.screen

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.domain.model.Screen
import woowacourse.movie.domain.repository.DummyScreens
import woowacourse.movie.ui.detail.ScreenDetailActivity
import woowacourse.movie.ui.screen.adapter.ScreenAdapter

class ScreenActivity : AppCompatActivity(), ScreenContract.View {
    private val listView: ListView by lazy { findViewById(R.id.lv_screen) }

    private lateinit var adapter: ScreenAdapter
    private val screenPresenter: ScreenContract.Presenter by lazy { ScreenPresenter(this, DummyScreens()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initAdapter()
        screenPresenter.loadScreens()
    }

    private fun initAdapter() {
        adapter = ScreenAdapter(emptyList()) { screenId ->
            ScreenDetailActivity.startActivity(this, screenId)
        }
        listView.adapter = adapter
    }

    override fun showScreens(screens: List<Screen>) {
        adapter.updateScreens(screens)
    }
}
