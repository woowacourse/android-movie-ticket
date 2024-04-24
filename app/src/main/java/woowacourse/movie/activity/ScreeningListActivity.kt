package woowacourse.movie.activity

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.adapter.ScreeningAdapter
import woowacourse.movie.contract.ScreeningListContract
import woowacourse.movie.model.screening.Screening
import woowacourse.movie.presenter.ScreeningListPresenter

class ScreeningListActivity : AppCompatActivity(), ScreeningListContract.View {
    private lateinit var screeningListView: ListView
    private lateinit var screeningAdapter: ScreeningAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_list_activity)
        val presenter =
            ScreeningListPresenter(
                view = this,
            )
        presenter.loadScreenings()
        screeningListView = findViewById(R.id.movies_list_item)
        screeningListView.adapter = screeningAdapter
    }

    override fun displayScreenings(screenings: List<Screening>) {
        screeningAdapter = ScreeningAdapter(this)
        screeningAdapter.setScreenings(screenings)
    }

    override fun navigateToScreeningDetail(screeningId: Int) {
        val intent =
            Intent(this, ScreeningDetailActivity::class.java).apply {
                putExtra("ScreeningId", screeningId)
            }
        startActivity(intent)
    }
}
