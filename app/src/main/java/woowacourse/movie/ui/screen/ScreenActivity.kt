package woowacourse.movie.ui.screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.ui.screen.adapter.ScreenAdapter
import woowacourse.movie.domain.repository.DummyScreens

class ScreenActivity : AppCompatActivity() {
    private lateinit var adapter: ScreenAdapter
    private val screenViewModel: ScreenViewModel by lazy { ScreenViewModel(DummyScreens()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initAdapter()
    }

    private fun initAdapter() {
        val item = screenViewModel.load()
        adapter = ScreenAdapter(item)
        val listView = findViewById<android.widget.ListView>(R.id.lv_screen)
        listView.adapter = adapter
    }
}
