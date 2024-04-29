package woowacourse.movie.presentation.reservation.seat

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import woowacourse.movie.R
import woowacourse.movie.data.MovieDao
import woowacourse.movie.model.Seats
import woowacourse.movie.model.Ticket
import woowacourse.movie.presentation.reservation.result.ReservationResultActivity
import woowacourse.movie.presentation.screen.detail.MovieDetailActivity.Companion.TICKET
import woowacourse.movie.presentation.screen.movie.ScreeningMovieActivity.Companion.MOVIE_ID
import woowacourse.movie.util.bundleSerializable

class ReservationSeatActivity : AppCompatActivity(), ReservationSeatContract.View {
    private val presenter: ReservationSeatPresenter by lazy {
        ReservationSeatPresenter(this, MovieDao())
    }
    private val titleTextView: TextView by lazy { findViewById(R.id.seat_title_textview) }
    private val tableLayout: TableLayout by lazy { findViewById(R.id.seat_table_layout) }
    private val priceTextView: TextView by lazy { findViewById(R.id.seat_selection_price) }
    private val complete: LinearLayout by lazy { findViewById(R.id.reservation_complete_linear_layout) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_seat)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        presenter.fetch(intent)
        addClickListener()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.apply {
            putSerializable(SEATS, presenter.seats)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val seats = savedInstanceState.bundleSerializable(SEATS, Seats::class.java)
            ?: error("seats 정보가 없습니다.")
        seats.let { seats ->
            seats.seats.forEach { seat ->
                presenter.onClickedSeat(seat.row, seat.col)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }

    override fun setUpView(title: String) {
        titleTextView.text = title
    }

    override fun showSeatPrice(price: Int) {
        priceTextView.text = getString(R.string.price_format, price)
    }

    override fun updateSeatColorToWhite(
        row: Int,
        col: Int,
    ) {
        val tableRow = tableLayout.getChildAt(row) as TableRow
        val textView = tableRow.getChildAt(col) as TextView
        textView.setBackgroundColor(getColor(R.color.white))
    }

    override fun updateSeatColorToYellow(
        row: Int,
        col: Int,
    ) {
        val tableRow = tableLayout.getChildAt(row) as TableRow
        val textView = tableRow.getChildAt(col) as TextView
        textView.setBackgroundColor(getColor(R.color.yellow))
    }

    override fun ableClickCompleteText() {
        complete.setBackgroundColor(getColor(R.color.custom_purple))
        complete.setOnClickListener {
            AlertDialog.Builder(this)
                .setCancelable(false)
                .setTitle(DIALOG_TITLE)
                .setMessage(DIALOG_MESSAGE)
                .setPositiveButton(DIALOG_POSITIVE) { _, _ -> presenter.loadReservationInfo() }
                .setNegativeButton(DIALOG_NEGATIVE) { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }

    override fun unableClickCompleteText() {
        complete.setBackgroundColor(Color.GRAY)
        complete.setOnClickListener(null)
    }

    override fun navigateToResult(
        seats: Seats,
        ticket: Ticket,
        movieId: Int,
    ) {
        val intent = Intent(this, ReservationResultActivity::class.java)
        intent.putExtra(MOVIE_ID, movieId)
        intent.putExtra(TICKET, ticket)
        intent.putExtra(SEATS, seats)
        this.startActivity(intent)
    }

    private fun getSeatTextView(): Sequence<TextView> {
        return tableLayout.children
            .filterIsInstance<TableRow>()
            .flatMap { it.children }
            .filterIsInstance<TextView>()
    }

    private fun addClickListener() {
        getSeatTextView().forEach { textView ->
            textView.setOnClickListener {
                val tableRowIndex = tableLayout.indexOfChild(textView.parent as TableRow)
                val textViewIndex = (textView.parent as TableRow).indexOfChild(textView)

                presenter.onClickedSeat(tableRowIndex, textViewIndex)
                presenter.checkSeatsCount()
            }
        }
    }

    companion object {
        const val SEATS = "seats"
        const val DIALOG_TITLE = "예매 확인"
        const val DIALOG_MESSAGE = "정말 예매하시겠습니까?"
        const val DIALOG_POSITIVE = "예매완료"
        const val DIALOG_NEGATIVE = "취소"
    }
}
