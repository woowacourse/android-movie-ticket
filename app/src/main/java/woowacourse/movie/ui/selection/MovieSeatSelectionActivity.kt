package woowacourse.movie.ui.selection

import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.children
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_seat_selection)

        presenter.loadTheater()
        presenter.loadMovieTitle(movieContentId)
        presenter.loadTotalSeatAmount()
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
                    }
                }
            }
        }
    }

    override fun showSelectedSeat(
        row: Int,
        col: Int,
    ) {
        seatTable.children.filterIsInstance<TableRow>().flatMap { it.children }
            .filterIsInstance<TextView>().toList()[positionToIndex(row, col)].setBackgroundColor(
            ContextCompat.getColor(this, R.color.selected_seat),
        )
    }

    override fun showUnSelectedSeat(
        row: Int,
        col: Int,
    ) {
        seatTable.children.filterIsInstance<TableRow>().flatMap { it.children }
            .filterIsInstance<TextView>().toList()[positionToIndex(row, col)].setBackgroundColor(
            ContextCompat.getColor(this, R.color.unSelected_seat),
        )
    }

    override fun showReservationTotalAmount(amount: Int) {
        totalSeatAmount.text =
            resources.getString(R.string.seat_amount)
                .format(amount)
    }

    override fun showError(throwable: Throwable) {
    }

    companion object {
        private val TAG = MovieSeatSelectionActivity::class.simpleName
        private const val MOVIE_CONTENT_ID_DEFAULT_VALUE = -1L
        private const val RESERVATION_COUNT_DEFAULT_VALUE = -1
        private const val SELECTED_DATE_DEFAULT_VALUE = -1
        private const val SELECTED_TIME_DEFAULT_VALUE = -1
    }
}
