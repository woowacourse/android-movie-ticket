package woowacourse.movie.feature.main

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.data.MockScreeningRepository
import woowacourse.movie.feature.main.ui.ListViewAdapter
import woowacourse.movie.feature.main.ui.ScreeningModel
import woowacourse.movie.feature.reservation.ReservationActivity

class MainActivity : AppCompatActivity(), MainContract.View {
    private val screeningListView: ListView by lazy {
        findViewById(R.id.list_view)
    }
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = MainPresenter(this, MockScreeningRepository)
        presenter.fetchScreeningList()
    }

    override fun displayScreenings(screeningModels: List<ScreeningModel>) {
        screeningListView.adapter =
            ListViewAdapter(screeningModels) { position ->
                presenter.selectMovie(screeningModels[position].id)
            }
    }

    override fun navigateToReservationScreen(id: Long) {
        startActivity(ReservationActivity.getIntent(this, id))
    }
}
