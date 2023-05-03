package woowacourse.movie.view.activities.screeninglist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.domain.Screening
import woowacourse.movie.view.*
import woowacourse.movie.view.ReservationActivity.Companion.SCREENING_ID

class ScreeningListActivity : AppCompatActivity(), ScreeningListContract.View {

    private lateinit var presenter: ScreeningListContract.Presenter
    private val screeningListView by lazy { findViewById<RecyclerView>(R.id.screening_list_view) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screening_list)

        presenter = ScreeningListPresenter(this)
    }

    override fun setScreeningList(screenings: List<Screening>) {
        val screeningListViewUIStates = createScreeningListViewUIStates(screenings)
        screeningListView.adapter = ScreeningListAdapter(screeningListViewUIStates) { screeningId ->
            val intent = Intent(this, ReservationActivity::class.java)
            intent.putExtra(SCREENING_ID, screeningId)
            startActivity(intent)
        }
    }

    private fun createScreeningListViewUIStates(screenings: List<Screening>): List<ScreeningListViewItemUIState> {
        val screeningListViewUIStates = mutableListOf<ScreeningListViewItemUIState>()

        screenings.forEachIndexed { index, screening ->
            val screeningId = screening.id ?: return@forEachIndexed
            screeningListViewUIStates.add(
                ScreeningListViewItemUIState.ScreeningUIState.of(
                    screening,
                    getPosterId(screeningId)
                )
            )
            if ((index + 1) % ADVERTISE_INTERVAL == 0)
                screeningListViewUIStates.add(ScreeningListViewItemUIState.AdvertisementUIState(R.drawable.ad_image))
        }

        return screeningListViewUIStates
    }

    private fun getPosterId(screeningId: Long): Int =
        when (screeningId) {
            1L -> R.drawable.harry_porter1_poster
            2L -> R.drawable.harry_porter2_poster
            3L -> R.drawable.harry_porter3_poster
            4L -> R.drawable.harry_porter4_poster
            5L -> R.drawable.harry_porter5_poster
            6L -> R.drawable.harry_porter6_poster
            7L -> R.drawable.harry_porter7_poster
            else -> R.drawable.harry_porter8_poster
        }

    companion object {
        private const val ADVERTISE_INTERVAL = 3
    }
}
