package woowacourse.movie.presentation.ui.seatselection

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.children
import woowacourse.movie.R
import woowacourse.movie.domain.model.Screen
import woowacourse.movie.domain.model.Seat
import woowacourse.movie.domain.model.SeatRank
import woowacourse.movie.domain.repository.DummyScreens
import woowacourse.movie.presentation.base.BaseActivity
import woowacourse.movie.presentation.model.ReservationInfo
import woowacourse.movie.presentation.ui.seatselection.SeatSelectionContract.Presenter
import woowacourse.movie.presentation.ui.seatselection.SeatSelectionContract.View
import java.io.Serializable

class SeatSelectionActivity : BaseActivity(), View {
    override val layoutResourceId: Int
        get() = R.layout.activity_seat_selection
    override val presenter: Presenter by lazy { SeatSelectionPresenter(this, DummyScreens()) }

    private val seatBoard: TableLayout by lazy { findViewById(R.id.tl_seat_board) }
    private val title: TextView by lazy { findViewById(R.id.tv_screen_title) }
    private val totalPrice: TextView by lazy { findViewById(R.id.tv_screen_total_price) }
    private val btnDone: Button by lazy { findViewById(R.id.btn_done) }

    override fun initStartView() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val reservationInfo =
            intent.getSerializableExtra(PUT_EXTRA_KEY_RESERVATION_INFO) as ReservationInfo

        presenter.updateUiModel(reservationInfo)
        presenter.loadScreen(reservationInfo.screenId)
        presenter.loadSeatBoard(reservationInfo.screenId)
    }

    override fun showScreen(screen: Screen) {
        with(screen) {
            title.text = movie.title
            totalPrice.text = "0원"
            btnDone.isEnabled = false
        }
    }

    override fun showSeatBoard(seats: List<Seat>) {
        seatBoard.children.filterIsInstance<TableRow>().flatMap { it.children }
            .filterIsInstance<TextView>().forEachIndexed { idx, view ->
                view.text = "${seats[idx].column}${seats[idx].row + 1}"
                view.setTextColor(seats[idx].seatRank.toColor())
            }
    }

    override fun initClickListener(
        ticketCount: Int,
        seats: List<Seat>,
    ) {
        seatBoard.children.filterIsInstance<TableRow>().flatMap { it.children }
            .filterIsInstance<TextView>().forEachIndexed { idx, view ->
                view.setOnClickListener {
                    presenter.clickSeat(seats[idx])
                    presenter.calculateSeat()
                    presenter.checkAllSeatsSelected()
                }
            }

        btnDone.setOnClickListener {}
    }

    private fun SeatRank.toColor(): Int {
        return when (this) {
            SeatRank.S -> getColor(R.color.purple)
            SeatRank.A -> getColor(R.color.green)
            SeatRank.B -> getColor(R.color.blue)
        }
    }

    override fun selectSeat(
        column: Int,
        row: Int,
    ) {
        seatBoard.children.filterIsInstance<TableRow>().flatMap { it.children }
            .filterIsInstance<TextView>().toList()[column * 4 + row].setBackgroundColor(
            ContextCompat.getColor(this, R.color.yellow),
        )
    }

    override fun unselectSeat(
        column: Int,
        row: Int,
    ) {
        seatBoard.children.filterIsInstance<TableRow>().flatMap { it.children }
            .filterIsInstance<TextView>().toList()[column * 4 + row].setBackgroundColor(
            ContextCompat.getColor(this, R.color.white),
        )
    }

    override fun showTotalPrice(totalPrice: Int) {
        this.totalPrice.text = "${totalPrice}원"
    }

    override fun buttonEnabled(isActivate: Boolean) {
        btnDone.isEnabled = isActivate
    }

    override fun navigateToReservation(id: Int) {}

    override fun back() = finish()

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        back()
        return true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
    }

    companion object {
        private const val PUT_EXTRA_KEY_RESERVATION_INFO = "reservationInfo"

        fun startActivity(
            context: Context,
            reservationInfo: ReservationInfo,
        ) {
            val intent = Intent(context, SeatSelectionActivity::class.java)
            intent.putExtra(PUT_EXTRA_KEY_RESERVATION_INFO, reservationInfo as Serializable)
            context.startActivity(intent)
        }
    }
}
