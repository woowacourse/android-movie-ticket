package woowacourse.movie.ui.screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.domain.model.Screen
import woowacourse.movie.domain.repository.DummyScreens
import woowacourse.movie.ui.screen.adapter.ScreenAdapter

class ScreenActivity : AppCompatActivity(), ScreenContract.View {
    private lateinit var adapter: ScreenAdapter
    private val screenPresenter: ScreenContract.Presenter by lazy { ScreenPresenter(this, DummyScreens()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        screenPresenter.loadScreens()
    }

    override fun showScreens(screens: List<Screen>) {
        adapter = ScreenAdapter(screens)
        val listView = findViewById<android.widget.ListView>(R.id.lv_screen)
        listView.adapter = adapter
    }
}
