package woowacourse.movie.view.activities.screeninglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.view.activities.screeningdetail.ScreeningDetailActivity

class ScreeningListActivity : AppCompatActivity(), ScreeningListContract.View {

    private val presenter: ScreeningListContract.Presenter = ScreeningListPresenter(this)
    private val screeningListView by lazy { findViewById<RecyclerView>(R.id.screening_list_view) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screening_list)

        presenter.loadScreenings()
    }

    override fun setScreeningList(screeningListViewItemUIStates: List<ScreeningListViewItemUIState>) {
        screeningListView.adapter = ScreeningListAdapter(screeningListViewItemUIStates) { screeningId ->
            ScreeningDetailActivity.startActivity(this, screeningId)
        }
    }
}
