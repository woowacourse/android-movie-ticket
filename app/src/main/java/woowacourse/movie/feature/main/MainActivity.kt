package woowacourse.movie.feature.main

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.data.MockScreeningRepository
import woowacourse.movie.feature.main.adapter.ScreeningAdapter
import woowacourse.movie.feature.main.ui.ScreeningItem
import woowacourse.movie.feature.reservation.ReservationActivity

class MainActivity : AppCompatActivity(), MainContract.View {
    private val rvScreening: RecyclerView by lazy {
        findViewById(R.id.rv_screening)
    }
    private lateinit var presenter: MainPresenter
    private lateinit var screeningAdapter: ScreeningAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = MainPresenter(this, MockScreeningRepository)
        setTmpButtonSetting()
    }

    private fun setTmpButtonSetting() {
        findViewById<Button>(R.id.btn_tmp).setOnClickListener {
            presenter.fetchScreeningList()
        }
    }

    override fun displayScreenings(screeningItems: MutableList<ScreeningItem>) {
        rvScreening.layoutManager = LinearLayoutManager(this)
        screeningAdapter =
            ScreeningAdapter(screeningItems) { screeningId ->
                presenter.selectScreening(screeningId)
            }
        rvScreening.adapter = screeningAdapter
    }

    override fun updateScreeningList(
        positionStart: Int,
        itemCount: Int,
    ) {
        rvScreening.adapter?.notifyItemRangeInserted(itemCount, positionStart)
    }

    override fun navigateToReservationScreen(screeningId: Long) {
        startActivity(ReservationActivity.getIntent(this, screeningId))
    }
}
