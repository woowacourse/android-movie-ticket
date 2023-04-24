package woowacourse.movie.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.model.main.MainModelHandler
import woowacourse.movie.ui.booking.BookingActivity
import woowacourse.movie.ui.main.adapter.recyclerview.MainAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initAdapter()
    }

    private fun initAdapter() {
        val mainAdapter = MainAdapter(
            this,
            { clickBook(it) },
            { clickAdvertisement(it) },
        )
        findViewById<RecyclerView>(R.id.listMainMovie).adapter = mainAdapter
        mainAdapter.initMovies(MainModelHandler.getMainData())
    }

    private fun clickBook(movieId: Long) {
        startActivity(BookingActivity.getIntent(this, movieId))
    }

    private fun clickAdvertisement(intent: Intent) {
        startActivity(intent)
    }
}
