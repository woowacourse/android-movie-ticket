package woowacourse.movie.feature.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.data.MockScreeningRepository
import woowacourse.movie.feature.main.adapter.ScreeningAdapter
import woowacourse.movie.feature.main.ui.ScreeningModel
import woowacourse.movie.feature.reservation.ReservationActivity

class MainActivity : AppCompatActivity(), MainContract.View {
    private val rvScreening: RecyclerView by lazy {
        findViewById(R.id.rv_screening)
    }
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = MainPresenter(this, MockScreeningRepository)
        presenter.fetchScreeningList()
    }

    override fun displayScreenings(
        screeningModels: List<ScreeningModel>,
        adImageResources: List<Int>,
    ) {
        rvScreening.layoutManager = LinearLayoutManager(this)
        rvScreening.adapter =
            ScreeningAdapter(screeningModels, adImageResources) { screeningId ->
                presenter.selectScreening(screeningId)
            }
    }

    override fun navigateToReservationScreen(screeningId: Long) {
        startActivity(ReservationActivity.getIntent(this, screeningId))
    }
}
