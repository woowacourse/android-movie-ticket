package woowacourse.movie.view.activities.screeningdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.view.PosterResourceProvider
import java.time.format.DateTimeFormatter

class ScreeningDetailActivity : AppCompatActivity(), ScreeningDetailContract.View {

    private val presenter: ScreeningDetailContract.Presenter = ScreeningDetailPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screening_detail)

        presenter.loadScreeningData(getScreeningIdFromIntent())
    }

    private fun getScreeningIdFromIntent(): Long {
        return intent.getLongExtra(SCREENING_ID, -1)
    }

    override fun setScreening(screeningDetailUIState: ScreeningDetailUIState) {
        val posterView = findViewById<ImageView>(R.id.movie_poster_iv)
        val titleView = findViewById<TextView>(R.id.movie_title_tv)
        val screeningRangeView = findViewById<TextView>(R.id.screening_range_tv)
        val runningTimeView = findViewById<TextView>(R.id.running_time_tv)
        val summaryView = findViewById<TextView>(R.id.movie_summary_tv)

        posterView.setImageResource(PosterResourceProvider.getPosterResourceId(screeningDetailUIState.screeningId))
        titleView.text = screeningDetailUIState.title
        screeningRangeView.text = getString(R.string.screening_range_format)
            .format(
                DATE_FORMATTER.format(screeningDetailUIState.screeningStartDate),
                DATE_FORMATTER.format(screeningDetailUIState.screeningEndDate)
            )
        runningTimeView.text = getString(R.string.running_time_format)
            .format(screeningDetailUIState.runningTime)
        summaryView.text = screeningDetailUIState.summary
    }

    companion object {
        const val SCREENING_ID = "SCREENING_ID"
        private val DATE_FORMATTER: DateTimeFormatter =
            DateTimeFormatter.ofPattern("yyyy.MM.dd")
    }
}
