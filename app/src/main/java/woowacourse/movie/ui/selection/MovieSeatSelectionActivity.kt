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
import woowacourse.movie.model.data.UserTicketsImpl
import woowacourse.movie.model.movie.Seat
import woowacourse.movie.ui.base.BaseActivity
import woowacourse.movie.ui.complete.MovieReservationCompleteActivity
import woowacourse.movie.ui.utils.positionToIndex

class MovieSeatSelectionActivity :
    BaseActivity<MovieSeatSelectionContract.Presenter>(),
    MovieSeatSelectionContract.View {
    private val userTicketId by lazy { userTicketId() }
    private val seatTable by lazy { findViewById<TableLayout>(R.id.seat_table) }
    private val seats by lazy { seatTable.makeSeats() }
    private val movieTitle by lazy { findViewById<TextView>(R.id.movie_title_text) }
    private val totalSeatAmount by lazy { findViewById<TextView>(R.id.total_seat_amount_text) }
    private val confirmButton by lazy { findViewById<Button>(R.id.confirm_button) }
    private val selectedSeatInfo = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_seat_selection)

        presenter.loadTheaterInfo(userTicketId)
        presenter.updateSelectCompletion()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setOnConfirmButtonListener()
    }

    private fun TableLayout.makeSeats(): List<TextView> =
        children.filterIsInstance<TableRow>().flatMap { it.children }
            .filterIsInstance<TextView>().toList()

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putIntegerArrayList(
            MovieSeatSelectionKey.SEAT_INFO,
            selectedSeatInfo as ArrayList<Int>,
        )
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        val seats = savedInstanceState.getIntegerArrayList(MovieSeatSelectionKey.SEAT_INFO)
        seats?.let {
            it.forEach { seatIndex ->
                presenter.recoverSeatSelection(seatIndex)
                selectedSeatInfo.add(seatIndex)
            }
        }
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
                presenter.reserveMovie(userTicketId)
            }
            .setNegativeButton(R.string.cancel) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun userTicketId() =
        intent.getLongExtra(
            MovieSeatSelectionKey.TICKET_ID,
            MOVIE_CONTENT_ID_DEFAULT_VALUE,
        )

    override fun initializePresenter(): MovieSeatSelectionContract.Presenter = MovieSeatSelectionPresenter(this, UserTicketsImpl)

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
            seats[positionToIndex(row, col)].apply {
                text = Seat(row, col).toString()
                setOnClickListener {
                    presenter.selectSeat(row, col)
                    selectedSeatInfo.add(positionToIndex(row, col))
                }
            }
        }
    }

    override fun showSelectedSeat(index: Int) {
        seats[index]
            .setBackgroundColor(
                ContextCompat.getColor(this, R.color.selected_seat),
            )
    }

    override fun showUnSelectedSeat(index: Int) {
        seats[index]
            .setBackgroundColor(
                ContextCompat.getColor(this, R.color.unSelected_seat),
            )
    }

    override fun showReservationTotalAmount(amount: Int) {
        totalSeatAmount.text =
            resources.getString(R.string.seat_amount)
                .format(amount)
    }

    override fun updateSelectCompletion(isComplete: Boolean) {
        confirmButton.apply {
            isEnabled = isComplete
            isClickable = isComplete
        }
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
    }
}
