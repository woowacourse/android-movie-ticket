package woowacourse.movie.ui.completed

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.formatScreenDateTime
import woowacourse.movie.model.ReservationUiModel
import woowacourse.movie.movie.MovieRepository
import woowacourse.movie.util.getParcelable

class CompletedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_completed)

        getResult()?.let { initView(it) } ?: finish()
    }

    private fun getResult(): ReservationUiModel? {
        return intent.getParcelable(RESERVATION, ReservationUiModel::class.java)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initView(reservation: ReservationUiModel) {
        val movie = MovieRepository.getMovie(reservation.movieId)
        findViewById<TextView>(R.id.textCompletedTitle).text = movie.title
        findViewById<TextView>(R.id.textCompletedScreeningDate).text =
            reservation.bookedDateTime.formatScreenDateTime()
        findViewById<TextView>(R.id.textCompletedTicketCount).text =
            getString(R.string.ticket_count_seat_info, reservation.count, reservation.seatPosition)
        findViewById<TextView>(R.id.textCompletedPaymentAmount).text =
            getString(R.string.payment_amount, reservation.payment)
        showBackButton()
    }

    private fun showBackButton() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    companion object {
        private const val RESERVATION = "RESERVATION"

        fun getIntent(context: Context, reservation: ReservationUiModel): Intent {
            return Intent(context, CompletedActivity::class.java).apply {
                putExtra(RESERVATION, reservation)
            }
        }
    }
}
