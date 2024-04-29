package woowacourse.movie.ui.complete

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import woowacourse.movie.R
import woowacourse.movie.model.data.UserTicketsImpl
import woowacourse.movie.model.movie.UserTicket
import woowacourse.movie.ui.base.BaseActivity
import woowacourse.movie.ui.home.MovieHomeActivity
import java.time.format.DateTimeFormatter

class MovieReservationCompleteActivity :
    BaseActivity<MovieReservationCompleteContract.Presenter>(),
    MovieReservationCompleteContract.View {
    private val titleText by lazy { findViewById<TextView>(R.id.title_text) }
    private val screeningDateTimeText by lazy { findViewById<TextView>(R.id.screening_date_time_text) }
    private val reservationCountText by lazy { findViewById<TextView>(R.id.reservation_count_text) }
    private val reservationSeatText by lazy { findViewById<TextView>(R.id.reservation_seat_text) }
    private val reservationAmountText by lazy { findViewById<TextView>(R.id.reservation_amount_text) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_reservation_complete)

        val userTicketId = userTicketId()
        if (userTicketId == USER_TICKET_ID_DEFAULT_VALUE) {
            presenter.handleError(NoSuchElementException())
            return
        }

        presenter.loadTicket(userTicketId)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun initializePresenter() = MovieReservationCompletePresenter(this, UserTicketsImpl)

    private fun userTicketId() = intent.getLongExtra(MovieReservationCompleteKey.TICKET_ID, USER_TICKET_ID_DEFAULT_VALUE)

    override fun showError(throwable: Throwable) {
        Log.e(TAG, throwable.message.toString())
        Toast.makeText(this, resources.getString(R.string.invalid_key), Toast.LENGTH_LONG).show()
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> startActivity(Intent(this, MovieHomeActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showTicket(userTicket: UserTicket) {
        userTicket.run {
            titleText.text = title
            screeningDateTimeText.text =
                screeningStartDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
            reservationCountText.text =
                resources.getString(R.string.reservation_count)
                    .format(reservationDetail.selectedSeat.size)
            reservationSeatText.text = userTicket.reservationDetail.selectedSeat.joinToString()
            reservationAmountText.text =
                resources.getString(R.string.reservation_amount)
                    .format(userTicket.reservationDetail.totalSeatAmount())
        }
    }

    companion object {
        private val TAG = MovieReservationCompleteActivity::class.simpleName
        private const val USER_TICKET_ID_DEFAULT_VALUE = -1L
    }
}
