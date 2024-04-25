package woowacourse.movie.ui.selection

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.core.view.forEach
import androidx.core.view.forEachIndexed
import woowacourse.movie.R
import woowacourse.movie.model.data.MovieContentsImpl
import woowacourse.movie.model.movie.Seat
import woowacourse.movie.ui.base.BaseActivity
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
    }

    private fun movieContentId() =
        intent.getLongExtra(
            MovieSeatSelectionKey.ID,
            MOVIE_CONTENT_ID_DEFAULT_VALUE,
        )

    private fun reservationCount() =
        intent.getIntExtra(
            MovieSeatSelectionKey.COUNT,
            RESERVATION_COUNT_DEFAULT_VALUE,
        )

    private fun selectedDate() =
        intent.getIntExtra(
            MovieSeatSelectionKey.DATE,
            SELECTED_DATE_DEFAULT_VALUE,
        )

    private fun selectedTime() =
        intent.getIntExtra(
            MovieSeatSelectionKey.TIME,
            SELECTED_TIME_DEFAULT_VALUE,
        )

    override fun initializePresenter(): MovieSeatSelectionContract.Presenter =
        MovieSeatSelectionPresenter(this, MovieContentsImpl, reservationCount)

    override fun showMovieTitle(title: String) {
        movieTitle.text = title
    }

    override fun showTheater(
        rowSize: Int,
        colSize: Int,
    ) {
        repeat(rowSize) { row ->
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
    }

    override fun showSelectedSeat(seats: List<Seat>) {
        seatTable.forEachIndexed { index, view ->
            (view as TableRow).forEach {
                it.setBackgroundColor(
                    ContextCompat.getColor(this, R.color.unSelected_seat),
                )
            }
        }

        seats.forEach {
            seatTable.children.filterIsInstance<TableRow>().flatMap { it.children }
                .filterIsInstance<TextView>().toList()[
                positionToIndex(
                    it.row.row,
                    it.col,
                ),
            ].setBackgroundColor(
                ContextCompat.getColor(this, R.color.selected_seat),
            )
        }
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
        } else {
            confirmButton.isEnabled = false
            confirmButton.isClickable = false
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
        private const val SELECTED_DATE_DEFAULT_VALUE = -1
        private const val SELECTED_TIME_DEFAULT_VALUE = -1
    }
}
