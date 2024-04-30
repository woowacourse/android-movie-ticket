package woowacourse.movie.presentation.ui.reservation

import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.data.repository.MovieTicketRepositoryImpl
import woowacourse.movie.presentation.base.BaseActivity
import woowacourse.movie.presentation.uimodel.MovieTicketUiModel

class ReservationResultActivity : BaseActivity(), ReservationResultContract.View {
    private lateinit var presenter: ReservationResultPresenter

    override fun getLayoutResId(): Int = R.layout.activity_reservation_result

    override fun onCreateSetup() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val movieTicketId = intent.getIntExtra(EXTRA_MOVIE_TICKET_ID, -1)

        presenter = ReservationResultPresenter(this, MovieTicketRepositoryImpl, movieTicketId)
    }

    override fun showTicketData(movieTicket: MovieTicketUiModel) {
        findViewById<TextView>(R.id.title).text = movieTicket.movieTitle
        findViewById<TextView>(R.id.screeningDate).text = movieTicket.screeningDate
        findViewById<TextView>(R.id.reservationCountAndSeats).text =
            getString(R.string.reservation_count_format, movieTicket.reservationCount, movieTicket.reservationSeats.toString())
        findViewById<TextView>(R.id.totalPrice).text =
            getString(R.string.total_price_format, movieTicket.totalPrice)
    }

    override fun showMessage(message: String) {
        showSnackBar(message)
    }

    companion object {
        const val EXTRA_MOVIE_TICKET_ID = "movieTicketId"
    }
}
