package woowacourse.movie

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.data.StubMovieRepository
import woowacourse.movie.presenter.toUiModel
import woowacourse.movie.repository.MovieRepository
import woowacourse.movie.view.ReservationResultUiModel

class ReservationResultActivity : AppCompatActivity(), ReservationResultView {
    private lateinit var presenter: ReservationResultPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_result)
        presenter =
            ReservationResultPresenter(
                id = intent.getLongExtra("reservationResultId", 0),
                repository = StubMovieRepository,
                view = this,
            )
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showResult(reservationResult: ReservationResultUiModel) {
        val (title, cancelDeadLine, date, count, totalPrice) = reservationResult
        findViewById<TextView>(R.id.tv_result_title).text = title
        findViewById<TextView>(R.id.tv_result_cancel_deadline).text =
            getString(R.string.reservation_cancel_deadline_format, cancelDeadLine)
        findViewById<TextView>(R.id.tv_result_running_date).text = date
        findViewById<TextView>(R.id.tv_result_count).text =
            getString(R.string.reservation_head_count_format, count)
        findViewById<TextView>(R.id.tv_result_total_price).text =
            getString(R.string.reservation_total_price_format, totalPrice)
    }
}

interface ReservationResultView {
    fun showResult(reservationResult: ReservationResultUiModel)
}

class ReservationResultPresenter(
    id: Long,
    private val repository: MovieRepository,
    private val view: ReservationResultView,
) {
    init {
        val reservationResult = repository.movieReservationById(id).toUiModel()
        view.showResult(reservationResult)
    }
}
