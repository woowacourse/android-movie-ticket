package woowacourse.movie.feature.seat

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.children
import woowacourse.movie.R
import woowacourse.movie.feature.seat.ui.SeatSelectMovieUiModel
import woowacourse.movie.feature.seat.ui.SeatSelectTableUiModel
import woowacourse.movie.model.Ticket
import woowacourse.movie.model.data.MovieRepositoryImpl
import woowacourse.movie.utils.BaseActivity
import java.lang.IllegalArgumentException

class SeatSelectActivity(
    private val seatRow: Int = 5,
    private val seatCol: Int = 4,
) : BaseActivity<SeatSelectContract.Presenter>(), SeatSelectContract.View {
    private val seatTable by lazy { findViewById<TableLayout>(R.id.seat_table) }
    private val titleText by lazy { findViewById<TextView>(R.id.title_text) }
    private val reservationAmountText by lazy { findViewById<TextView>(R.id.reservation_amount_text) }
    private val confirmButton by lazy { findViewById<Button>(R.id.confirm_button) }
    private lateinit var seatViews: List<List<TextView>>
    private val selectedSeat by lazy { listOf<String>() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_seat_select)

        val movieId = movieId()
        val ticket = ticket()
        if (isError(movieId, ticket)) {
            handleError(IllegalArgumentException(resources.getString(R.string.invalid_key)))
            return
        }

        seatViews =
            seatTable
                .children
                .filterIsInstance<TableRow>()
                .map { it.children.filterIsInstance<TextView>().toList() }
                .toList()

        updateReservationAmount(INITIAL_RESERVATION_AMOUNT)
        presenter.loadMovieData(movieId)
        presenter.initializeSeatTable(seatRow, seatCol)
    }

    override fun initializePresenter() =
        SeatSelectPresenter(this, ticket()!!.reservationCount, MovieRepositoryImpl)

    private fun movieId() = intent.getLongExtra(MOVIE_ID_KEY, MOVIE_ID_DEFAULT_VALUE)

    private fun ticket(): Ticket? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(TICKET_KEY, Ticket::class.java)
        } else {
            intent.getParcelableExtra(TICKET_KEY) as? Ticket
        }
    }

    private fun isError(
        movieId: Long,
        ticket: Ticket?,
    ): Boolean {
        return movieId == MOVIE_ID_DEFAULT_VALUE || ticket == null
    }

    override fun handleError(throwable: Throwable) {
        Log.d(TAG, throwable.stackTrace.toString())
        Toast.makeText(this, throwable.localizedMessage, Toast.LENGTH_LONG).show()
        finish()
    }

    override fun initializeMovie(movie: SeatSelectMovieUiModel) {
        titleText.text = movie.titleMessage
    }

    override fun initializeSeatTable(seats: List<List<SeatSelectTableUiModel>>) {
        seatViewsIndexed { row, col, seatView ->
            seatView.text = seats[row][col].seatMessage
            val color = ContextCompat.getColor(this, seats[row][col].seatColorId)
            seatView.setTextColor(color)
            seatView.setOnClickListener {
                presenter.selectSeat(row, col)
            }
        }

        confirmButton.setOnClickListener {

        }
    }

    private fun seatViewsIndexed(block: (Int, Int, TextView) -> Unit) {
        seatViews.forEachIndexed { row, rows ->
            rows.forEachIndexed { col, seatView ->
                block(row, col, seatView)
            }
        }
    }

    override fun updateReservationAmount(reservationAmountValue: Int) {
        reservationAmountText.text =
            resources.getString(R.string.seat_reservation_amount).format(reservationAmountValue)
    }

    override fun showCannotSelectSeat() {
        Toast.makeText(this, R.string.cannot_select_seat, Toast.LENGTH_SHORT).show()
    }

    override fun selectSeat(row: Int, col: Int, isConfirm: Boolean) {
        seatViews[row][col].setBackgroundColor(ContextCompat.getColor(this, R.color.yellow))
        if (isConfirm) {
            confirmButton.isEnabled = true
        }
    }

    override fun unselectSeat(row: Int, col: Int) {
        seatViews[row][col].setBackgroundColor(ContextCompat.getColor(this, R.color.white))
        confirmButton.isEnabled = false
    }

    companion object {
        private val TAG = SeatSelectActivity::class.simpleName
        private const val INITIAL_RESERVATION_AMOUNT = 0
        private const val MOVIE_ID_KEY = "movie_id"
        private const val MOVIE_ID_DEFAULT_VALUE = -1L
        private const val TICKET_KEY = "ticket_id"

        fun startActivity(
            context: Context,
            movieId: Long,
            ticket: Ticket,
        ) {
            Intent(context, SeatSelectActivity::class.java).run {
                putExtra(MOVIE_ID_KEY, movieId)
                putExtra(TICKET_KEY, ticket)
                context.startActivity(this)
            }
        }
    }
}
