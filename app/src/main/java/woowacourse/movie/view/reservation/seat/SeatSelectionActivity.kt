package woowacourse.movie.view.reservation.seat

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.children
import woowacourse.movie.R
import woowacourse.movie.domain.model.ReservationInfo
import woowacourse.movie.domain.model.Seat
import woowacourse.movie.view.base.BaseActivity
import woowacourse.movie.view.extension.getParcelableCompat
import woowacourse.movie.view.reservation.result.ReservationResultActivity

class SeatSelectionActivity :
    BaseActivity(R.layout.activity_seat_selection),
    SeatSelectionContract.View {
    private val presenter: SeatSelectionPresenter by lazy { SeatSelectionPresenter(this) }

    private val showReservationDialog by lazy {
        AlertDialog
            .Builder(this)
            .setTitle(R.string.reservation_dialog_title)
            .setMessage(R.string.reservation_dialog_message)
            .setCancelable(false)
            .setPositiveButton(R.string.reservation_dialog_positive) { _, _ ->
                submitReservation()
            }.setNegativeButton(R.string.reservation_dialog_negative) { dialog, _ -> dialog.dismiss() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val reservation = intent.getParcelableCompat<ReservationInfo>(BUNDLE_KEY_RESERVATION_INFO)

        presenter.loadSeats(reservation)
        setupViews(reservation.title)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressedDispatcher.onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupViews(title: String) {
        showMovieTitle(title)

        findViewById<Button>(R.id.btn_seat_select_confirm).setOnClickListener {
            presenter.showConfirmButton()
        }
    }

    override fun showSeats(seats: List<Seat>) {
        findViewById<TableLayout>(R.id.tl_seat)
            .children
            .filterIsInstance<TableRow>()
            .forEachIndexed { rowIndex, row ->
                row.children
                    .filterIsInstance<TextView>()
                    .forEachIndexed { colIndex, view ->
                        val seat =
                            seats.find { it.row == rowIndex && it.column == colIndex }
                                ?: return
                        setupSeatClickListener(view, seat)
                    }
            }
    }

    override fun updateSeatSelection(
        seat: Seat,
        isSelected: Boolean,
    ) {
        findViewById<TableLayout>(R.id.tl_seat)
            .children
            .filterIsInstance<TableRow>()
            .elementAtOrNull(seat.row)
            ?.children
            ?.filterIsInstance<TextView>()
            ?.elementAtOrNull(seat.column)
            ?.apply {
                background =
                    if (isSelected) {
                        ContextCompat.getDrawable(context, R.color.yellow)
                    } else {
                        ContextCompat.getDrawable(context, R.color.white)
                    }
            }
    }

    override fun showTotalPrice(price: Int) {
        val tvPrice = findViewById<TextView>(R.id.tv_seat_select_total_price)
        tvPrice.text = getString(R.string.reservation_total_money, price)
    }

    override fun showMovieTitle(title: String) {
        findViewById<TextView>(R.id.tv_seat_select_movie_title).text = title
    }

    override fun enableConfirmButton(enabled: Boolean) {
        findViewById<Button>(R.id.btn_seat_select_confirm).isEnabled = enabled
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showReservationDialog() {
        showReservationDialog.show()
    }

    override fun navigateToResult(reservationInfo: ReservationInfo) {
        startActivity(ReservationResultActivity.newIntent(this, reservationInfo))
    }

    private fun submitReservation() {
        presenter.completeReservation()
    }

    private fun setupSeatClickListener(
        view: TextView,
        seat: Seat,
    ) {
        view.setOnClickListener {
            presenter.selectSeat(seat)
        }
    }

    companion object {
        private const val BUNDLE_KEY_RESERVATION_INFO = "reservation_info"

        fun newIntent(
            context: Context,
            reservationInfo: ReservationInfo,
        ): Intent =
            Intent(context, SeatSelectionActivity::class.java).putExtra(
                BUNDLE_KEY_RESERVATION_INFO,
                reservationInfo,
            )
    }
}
