package woowacourse.movie.view.activities.screeninglist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.domain.Screening
import woowacourse.movie.view.PosterResourceProvider
import woowacourse.movie.view.activities.screeningdetail.ScreeningDetailActivity
import woowacourse.movie.view.activities.screeningdetail.ScreeningDetailActivity.Companion.SCREENING_ID

class ScreeningListActivity : AppCompatActivity(), ScreeningListContract.View {

    private val presenter: ScreeningListContract.Presenter = ScreeningListPresenter(this)
    private val screeningListView by lazy { findViewById<RecyclerView>(R.id.screening_list_view) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screening_list)

        presenter.loadScreenings()
    }

    override fun setScreeningList(screenings: List<Screening>) {
        val screeningListViewUIStates = createScreeningListViewUIStates(screenings)
        screeningListView.adapter = ScreeningListAdapter(screeningListViewUIStates) { screeningId ->
            val intent = Intent(this, ScreeningDetailActivity::class.java)
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
                    PosterResourceProvider.getPosterResourceId(screeningId)
                )
            )
            if ((index + 1) % ADVERTISE_INTERVAL == 0)
                screeningListViewUIStates.add(ScreeningListViewItemUIState.AdvertisementUIState(R.drawable.ad_image))
        }

        return screeningListViewUIStates
    }

    companion object {
        private const val ADVERTISE_INTERVAL = 3
    }
}
