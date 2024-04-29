package woowacourse.movie.ui.screen

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.domain.repository.DummyMovies
import woowacourse.movie.domain.repository.DummyScreens
import woowacourse.movie.ui.ScreenPreviewUI
import woowacourse.movie.ui.detail.ScreenDetailActivity
import woowacourse.movie.ui.screen.adapter.ScreenAdapter
import woowacourse.movie.ui.screen.adapter.ScreenViewHolderCaches

class ScreenActivity : AppCompatActivity(), ScreenContract.View {
    private lateinit var adapter: ScreenAdapter
    private val screenPresenter: ScreenContract.Presenter by lazy {
        ScreenPresenter(
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
        val listView = findViewById<ListView>(R.id.lv_screen)
        adapter =
            ScreenAdapter(
                emptyList(),
                ScreenViewHolderCaches { screenId ->
                    ScreenDetailActivity.startActivity(this, screenId)
                },
            )
        listView.adapter = adapter
    }

    override fun showScreens(screens: List<ScreenPreviewUI>) {
        adapter.updateScreens(screens)
    }
}
