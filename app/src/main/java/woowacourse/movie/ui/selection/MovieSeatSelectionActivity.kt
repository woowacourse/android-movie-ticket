package woowacourse.movie.ui.selection

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.children
import woowacourse.movie.R
import woowacourse.movie.model.data.MovieContentsImpl
import woowacourse.movie.model.data.UserTicketsImpl
import woowacourse.movie.model.movie.Seat
import woowacourse.movie.ui.base.BaseActivity
import woowacourse.movie.ui.complete.MovieReservationCompleteActivity
import woowacourse.movie.ui.utils.positionToIndex

class MovieSeatSelectionActivity :
    BaseActivity<MovieSeatSelectionContract.Presenter>(),
    MovieSeatSelectionContract.View {
    private val movieContentId by lazy { movieContentId() }
    private val reservationCount by lazy { reservationCount() }
    private val selectedDate by lazy { selectedDate() }
    private val selectedTime by lazy { selectedTime() }
    private val seatTable by lazy { findViewById<TableLayout>(R.id.seat_table) }
    private val movieTitle by lazy { findViewById<TextView>(R.id.movie_title_text) }
    private val totalSeatAmount by lazy { findViewById<TextView>(R.id.total_seat_amount_text) }
    private val confirmButton by lazy { findViewById<Button>(R.id.confirm_button) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_seat_selection)

        presenter.loadTheater()
        presenter.loadMovieTitle(movieContentId)
        presenter.loadTotalSeatAmount()
        presenter.updateSelectCompletion()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setOnConfirmButtonListener()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setOnConfirmButtonListener() {
        confirmButton.setOnClickListener {
            makeAlertDialog()
        }
    }

    private fun makeAlertDialog() {
        AlertDialog.Builder(this)
            .setCancelable(false)
            .setTitle(R.string.reservation_confirm)
            .setMessage(R.string.reservation_confirm_comment)
            .setPositiveButton(R.string.reservation_complete) { _, _ ->
                presenter.reserveMovie(
                    selectedDate,
                    selectedTime,
                )
            }
            .setNegativeButton(R.string.cancel) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun movieContentId() =
        intent.getLongExtra(
            MovieSeatSelectionKey.MOVIE_CONTENT_ID,
            MOVIE_CONTENT_ID_DEFAULT_VALUE,
        )

    private fun reservationCount() =
        intent.getIntExtra(
            MovieSeatSelectionKey.COUNT,
            RESERVATION_COUNT_DEFAULT_VALUE,
        )

    private fun selectedDate(): String = intent.getStringExtra(MovieSeatSelectionKey.DATE).toString()

    private fun selectedTime() = intent?.getStringExtra(MovieSeatSelectionKey.TIME).toString()

    override fun initializePresenter(): MovieSeatSelectionContract.Presenter =
        MovieSeatSelectionPresenter(this, MovieContentsImpl, UserTicketsImpl, reservationCount)

    override fun showMovieTitle(title: String) {
        movieTitle.text = title
    }

    override fun showTheater(
        rowSize: Int,
        colSize: Int,
    ) {
        repeat(rowSize) { row ->
            makeSeats(colSize, row)
        }
    }

    private fun makeSeats(
        colSize: Int,
        row: Int,
    ) {
        repeat(colSize) { col ->
            seatTable.children.filterIsInstance<TableRow>().flatMap { it.children }
                .filterIsInstance<TextView>().toList()[positionToIndex(row, col)].apply {
                text = Seat(row, col).toString()
                setOnClickListener {
                    presenter.selectSeat(row, col)
                    presenter.loadTotalSeatAmount()
                    presenter.updateSelectCompletion()
                }
            }
        }
    }

    override fun showSelectedSeat(
        row: Int,
        col: Int,
    ) {
        seatTable.children.filterIsInstance<TableRow>().flatMap { it.children }
            .filterIsInstance<TextView>().toList()[
            positionToIndex(row, col),
        ].setBackgroundColor(
            ContextCompat.getColor(this, R.color.selected_seat),
        )
    }

    override fun showUnSelectedSeat(
        row: Int,
        col: Int,
    ) {
        seatTable.children.filterIsInstance<TableRow>().flatMap { it.children }
            .filterIsInstance<TextView>().toList()[
            positionToIndex(row, col),
        ].setBackgroundColor(
            ContextCompat.getColor(this, R.color.unSelected_seat),
        )
    }

    override fun showReservationTotalAmount(amount: Int) {
        totalSeatAmount.text =
            resources.getString(R.string.seat_amount)
                .format(amount)
    }

    override fun updateSelectCompletion(isComplete: Boolean) {
        if (isComplete) {
            confirmButton.isEnabled = true
            confirmButton.isClickable = true
            return
        }
        confirmButton.isEnabled = false
        confirmButton.isClickable = false
    }

    override fun moveMovieReservationCompletePage(ticketId: Long) {
        Intent(this, MovieReservationCompleteActivity::class.java).run {
            putExtra(MovieSeatSelectionKey.TICKET_ID, ticketId)
            startActivity(this)
        }
    }

    override fun showError(throwable: Throwable) {
        Log.e(TAG, throwable.message.toString())
        Toast.makeText(this, resources.getString(R.string.invalid_key), Toast.LENGTH_LONG).show()
        finish()
    }

    companion object {
        private val TAG = MovieSeatSelectionActivity::class.simpleName
        private const val MOVIE_CONTENT_ID_DEFAULT_VALUE = -1L
        private const val RESERVATION_COUNT_DEFAULT_VALUE = -1
    }
}
