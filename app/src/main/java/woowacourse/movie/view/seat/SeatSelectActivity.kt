package woowacourse.movie.view.seat

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.children
import woowacourse.movie.R
import woowacourse.movie.common.parcelableExtraCompat
import woowacourse.movie.domain.Position
import woowacourse.movie.domain.SeatGrade
import woowacourse.movie.view.movie.model.MovieUiModel
import woowacourse.movie.view.reservation.MovieReservationCompleteActivity
import woowacourse.movie.view.reservation.model.TicketUiModel

class SeatSelectActivity :
    AppCompatActivity(),
    SeatSelectContract.View {
    private lateinit var presenter: SeatSelectPresenter

    private val seatTable: TableLayout by lazy { findViewById(R.id.seat_table) }
    private val confirmButton: Button by lazy { findViewById(R.id.confirm_button) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setView()

        val ticket =
            intent.parcelableExtraCompat(EXTRA_TICKET, TicketUiModel::class.java)
                ?: finish().run { return }
        presenter = SeatSelectPresenter(this, ticket)

        initView()
        presenter.loadSeatSelectScreen()
    }

    override fun showMovieInfo(movie: MovieUiModel) {
        findViewById<TextView>(R.id.movie_title).text = movie.title
    }

    override fun showTotalPrice(price: Int) {
        findViewById<TextView>(R.id.total_price).text = getString(R.string.total_price, price)
    }

    override fun showConfirmAlertDialog() {
        AlertDialog
            .Builder(this)
            .setTitle(R.string.confirm_reservation)
            .setMessage(R.string.confirm_reservation_message)
            .setPositiveButton(R.string.complete_reservation) { _, _ -> presenter.completeReservation() }
            .setNegativeButton(R.string.cancel) { dialog, _ -> dialog.dismiss() }
            .setCancelable(false)
            .show()
    }

    override fun showSelectToast() {
        Toast.makeText(this, R.string.select_seat, Toast.LENGTH_SHORT).show()
    }

    override fun updateSeatSelection(
        position: Position,
        isSelected: Boolean,
    ) {
        val seatTextView = seatTable.findViewWithTag<TextView>(position)
        seatTextView.setBackgroundResource(if (isSelected) R.color.yellow else R.color.white)
    }

    override fun updateConfirmButton(isEnabled: Boolean) {
        confirmButton.isEnabled = isEnabled
    }

    override fun navigateToCompleteScreen(ticket: TicketUiModel) {
        val intent = MovieReservationCompleteActivity.newIntent(this, ticket)
        startActivity(intent)
    }

    private fun setView() {
        enableEdgeToEdge()
        setContentView(R.layout.activity_seat_select)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun initView() {
        initSeatTable()
        initConfirmButton()
    }

    private fun initSeatTable() {
        seatTable.children.filterIsInstance<TableRow>().forEachIndexed { rowIdx, row ->
            val seatGrade = SeatGrade.of(rowIdx)
            row.children.filterIsInstance<TextView>().forEachIndexed { colIdx, seatTextView ->
                val position = Position(rowIdx, colIdx)
                seatTextView.tag = position
                seatTextView.text = "${'A' + rowIdx}${colIdx + 1}"
                when (seatGrade) {
                    SeatGrade.S ->
                        seatTextView.setTextColor(ContextCompat.getColor(this, R.color.green))

                    SeatGrade.A ->
                        seatTextView.setTextColor(ContextCompat.getColor(this, R.color.blue))

                    SeatGrade.B ->
                        seatTextView.setTextColor(ContextCompat.getColor(this, R.color.purple))
                }
                seatTextView.setOnClickListener {
                    presenter.onClickSeat(position)
                }
            }
        }
    }

    private fun initConfirmButton() {
        confirmButton.isEnabled = false
        confirmButton.setOnClickListener {
            presenter.onClickConfirmButton()
        }
    }

    companion object {
        fun newIntent(
            context: Context,
            ticket: TicketUiModel,
        ): Intent =
            Intent(context, SeatSelectActivity::class.java).apply {
                putExtra(EXTRA_TICKET, ticket)
            }

        private const val EXTRA_TICKET = "extra_ticket"
    }
}
