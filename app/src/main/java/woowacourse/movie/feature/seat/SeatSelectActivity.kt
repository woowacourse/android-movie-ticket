package woowacourse.movie.feature.seat

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.view.children
import woowacourse.movie.R
import woowacourse.movie.feature.complete.MovieReservationCompleteActivity
import woowacourse.movie.feature.seat.ui.SeatSelectMovieUiModel
import woowacourse.movie.feature.seat.ui.SeatSelectTableUiModel
import woowacourse.movie.model.data.MovieRepositoryImpl
import woowacourse.movie.model.data.TicketRepositoryImpl
import woowacourse.movie.model.reservation.ReservationCount
import woowacourse.movie.model.seat.SelectedSeats
import woowacourse.movie.utils.BaseActivity
import java.lang.IllegalArgumentException
import java.time.LocalDateTime

class SeatSelectActivity : BaseActivity<SeatSelectContract.Presenter>(), SeatSelectContract.View {
    private val seatTable by lazy { findViewById<TableLayout>(R.id.seat_table) }
    private val titleText by lazy { findViewById<TextView>(R.id.title_text) }
    private val reservationAmountText by lazy { findViewById<TextView>(R.id.reservation_amount_text) }
    private val confirmButton by lazy { findViewById<Button>(R.id.confirm_button) }
    private lateinit var selectedSeats: SelectedSeats
    private lateinit var seatViews: List<List<TextView>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_select)

        if (validateError()) return

        selectedSeats = SelectedSeats(ReservationCount(reservationCountValue()))
        seatViews =
            seatTable
                .children
                .filterIsInstance<TableRow>()
                .map { it.children.filterIsInstance<TextView>().toList() }
                .toList()

        initializeView()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val savedSelectedSeats =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                savedInstanceState.getParcelable(SELECTED_SEATS_KEY, SelectedSeats::class.java)
            } else {
                savedInstanceState.getParcelable(SELECTED_SEATS_KEY) as? SelectedSeats
            } ?: return
        selectedSeats = savedSelectedSeats
        presenter.updateSelectedSeats(selectedSeats)
    }

    override fun initializePresenter() = SeatSelectPresenter(this, MovieRepositoryImpl, TicketRepositoryImpl)

    private fun validateError(): Boolean {
        if (isError(movieId(), screeningDateTime(), reservationCountValue())) {
            handleError(IllegalArgumentException(resources.getString(R.string.invalid_key)))
            return true
        }
        return false
    }

    private fun movieId() = intent.getLongExtra(MOVIE_ID_KEY, MOVIE_ID_DEFAULT_VALUE)

    private fun screeningDateTime(): LocalDateTime? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(SCREENING_DATE_TIME_KEY, LocalDateTime::class.java)
        } else {
            intent.getSerializableExtra(SCREENING_DATE_TIME_KEY) as? LocalDateTime
        }
    }

    private fun reservationCountValue() = intent.getIntExtra(RESERVATION_COUNT_KEY, RESERVATION_COUNT_DEFAULT_VALUE)

    private fun isError(
        movieId: Long,
        screeningDateTime: LocalDateTime?,
        reservationCountValue: Int,
    ): Boolean {
        return movieId == MOVIE_ID_DEFAULT_VALUE || screeningDateTime == null || reservationCountValue == RESERVATION_COUNT_DEFAULT_VALUE
    }

    override fun handleError(throwable: Throwable) {
        Log.d(TAG, throwable.stackTrace.toString())
        Toast.makeText(this, throwable.localizedMessage, Toast.LENGTH_LONG).show()
        finish()
    }

    private fun initializeView() {
        presenter.loadMovieData(movieId())
        presenter.initializeSeatTable(selectedSeats, seatViews.size, seatViews[0].size)
        updateReservationAmount(INITIAL_RESERVATION_AMOUNT)
        confirmButton.setOnClickListener {
            presenter.confirmSeatSelection(movieId(), screeningDateTime()!!)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun initializeMovieView(movie: SeatSelectMovieUiModel) {
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
            resources.getString(R.string.seat_reservation_amount, reservationAmountValue)
    }

    override fun showCannotSelectSeat() {
        Toast.makeText(this, R.string.cannot_select_seat, Toast.LENGTH_LONG).show()
    }

    override fun selectSeat(
        row: Int,
        col: Int,
        isConfirm: Boolean,
    ) {
        seatViews[row][col].setBackgroundColor(ContextCompat.getColor(this, R.color.yellow))
        confirmButton.isEnabled = isConfirm
    }

    override fun unselectSeat(
        row: Int,
        col: Int,
    ) {
        seatViews[row][col].setBackgroundColor(ContextCompat.getColor(this, R.color.white))
        confirmButton.isEnabled = false
    }

    override fun moveReservationCompleteView(ticketId: Long) {
        AlertDialog.Builder(this)
            .setCancelable(false)
            .setTitle(resources.getString(R.string.reservation_confirm))
            .setMessage(resources.getString(R.string.reservation_question))
            .setPositiveButton(resources.getString(R.string.reservation_complete)) { _, _ ->
                MovieReservationCompleteActivity.startActivity(this, ticketId)
            }
            .setNegativeButton(resources.getString(R.string.cancel)) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable(
            SELECTED_SEATS_KEY,
            selectedSeats,
        )
        super.onSaveInstanceState(outState)
    }

    companion object {
        private val TAG = SeatSelectActivity::class.simpleName
        private const val INITIAL_RESERVATION_AMOUNT = 0
        private const val MOVIE_ID_KEY = "movie_id"
        private const val MOVIE_ID_DEFAULT_VALUE = -1L
        private const val SCREENING_DATE_TIME_KEY = "screening_date_time_key"
        private const val RESERVATION_COUNT_KEY = "reservation_count_key"
        private const val RESERVATION_COUNT_DEFAULT_VALUE = -1
        private const val SELECTED_SEATS_KEY = "selected_seats_key"

        fun startActivity(
            context: Context,
            movieId: Long,
            screeningDateTime: LocalDateTime,
            reservationCountValue: Int,
        ) {
            Intent(context, SeatSelectActivity::class.java).run {
                putExtra(MOVIE_ID_KEY, movieId)
                putExtra(SCREENING_DATE_TIME_KEY, screeningDateTime)
                putExtra(RESERVATION_COUNT_KEY, reservationCountValue)
                context.startActivity(this)
            }
        }
    }
}
