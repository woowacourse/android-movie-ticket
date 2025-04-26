package woowacourse.movie.view.seatSelection

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.children
import woowacourse.movie.R
import woowacourse.movie.model.movie.MovieToReserve
import woowacourse.movie.model.seat.Seat
import woowacourse.movie.model.seat.SeatGrade
import woowacourse.movie.model.ticket.MovieTicket
import woowacourse.movie.presenter.seatSelection.SeatSelectionContracts
import woowacourse.movie.presenter.seatSelection.SeatSelectionPresenter
import woowacourse.movie.view.extension.getSerializableExtraData
import woowacourse.movie.view.mapper.Formatter.priceToUI
import woowacourse.movie.view.reservationComplete.ReservationCompleteActivity
import woowacourse.movie.view.seatSelection.SeatSelectionFormatter.columnToUI
import woowacourse.movie.view.seatSelection.SeatSelectionFormatter.rowToUI

class SeatSelectionActivity :
    AppCompatActivity(),
    SeatSelectionContracts.View {
    private val presenter: SeatSelectionContracts.Presenter = SeatSelectionPresenter(this)
    private val seatsLayout: TableLayout by lazy { findViewById(R.id.tl_seat_selection) }
    private val titleTextView: TextView by lazy { findViewById(R.id.tv_seat_selection_movie_title) }
    private val seatPriceTextView: TextView by lazy { findViewById(R.id.tv_seat_selection_price) }
    private val completeButton: TextView by lazy { findViewById(R.id.tv_seat_selection_complete_button) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_selection)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.seat_selection_main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initView()
        setupClickListener()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initView() {
        presenter.loadSeats(
            getSeatsLayoutRowCount(),
            getSeatsLayoutColumnCount(),
        )
        presenter.updateMovieToReserve(
            intent.getSerializableExtraData<MovieToReserve>(MOVIE_TO_RESERVE_DATA_KEY),
        )
    }

    private fun getSeatsLayoutRowCount(): Int = seatsLayout.childCount

    private fun getSeatsLayoutColumnCount(): Int = (seatsLayout.getChildAt(0) as TableRow).childCount

    private fun setupClickListener() {
        completeButton.setOnClickListener {
            showReservationDialog()
        }
    }

    private fun showReservationDialog() {
        AlertDialog
            .Builder(this)
            .setTitle(getString(R.string.reservation_dialog_title))
            .setMessage(getString(R.string.reservation_dialog_message))
            .setCancelable(false)
            .setNegativeButton(getString(R.string.reservation_dialog_cancel)) { dialog, _ ->
                dialog.dismiss()
            }.setPositiveButton(getString(R.string.reservation_dialog_complete)) { dialog, _ ->
                presenter.onReservationCompleteRequest()
                dialog.dismiss()
            }.show()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    override fun showSeats(seats: List<Seat>) {
        seatsLayout.children
            .filterIsInstance<TableRow>()
            .forEachIndexed { rowIndex, row ->
                row.children
                    .filterIsInstance<TextView>()
                    .forEachIndexed { colIndex, view ->
                        val seat =
                            seats.find { it.row.value == rowIndex && it.column.value == colIndex }
                                ?: return
                        setupSeatText(seat, view)
                        setupSeatTextColor(seat, view)
                        setupSeatClickListener(view, seat)
                    }
            }
    }

    private fun setupSeatText(
        seat: Seat,
        view: TextView,
    ) {
        val key = "${rowToUI(seat.row.value)}${columnToUI(seat.column.value)}"
        view.setTag(R.id.seat_cell_key, key)
        view.text = key
    }

    private fun setupSeatTextColor(
        seat: Seat,
        view: TextView,
    ) {
        when (SeatGrade.calculateSeatGrade(seat.row)) {
            SeatGrade.B ->
                view.setTextColor(
                    ContextCompat.getColor(
                        view.context,
                        R.color.purple_8E13EF,
                    ),
                )

            SeatGrade.S ->
                view.setTextColor(
                    ContextCompat.getColor(
                        view.context,
                        R.color.green_19D358,
                    ),
                )

            SeatGrade.A ->
                view.setTextColor(
                    ContextCompat.getColor(
                        view.context,
                        R.color.blue_1B48E9,
                    ),
                )
        }
    }

    private fun setupSeatClickListener(
        view: TextView,
        seat: Seat,
    ) {
        view.setOnClickListener {
            view.isSelected = !view.isSelected
            presenter.updateSelectedSeat(seat)
        }
    }

    override fun showMovieTitle(title: String) {
        titleTextView.text = title
    }

    override fun showPrice(price: Int) {
        seatPriceTextView.text =
            resources.getString(
                R.string.seat_selection_ticket_price,
                priceToUI(price),
            )
    }

    override fun showButtonEnabled(enabled: Boolean) {
        completeButton.isEnabled = enabled
    }

    override fun updateSeatsEnabled(enabled: Boolean) {
        seatsLayout.children
            .filterIsInstance<TableRow>()
            .flatMap { row ->
                row.children.filterIsInstance<TextView>()
            }.filter { view -> view.isSelected.not() }
            .forEach { view ->
                view.isEnabled = enabled
            }
    }

    override fun showReservationCompleteView(movieTicket: MovieTicket) {
        startActivity(ReservationCompleteActivity.getIntent(this, movieTicket))
        finish()
    }

    companion object {
        private const val MOVIE_TO_RESERVE_DATA_KEY = "movieReserve"

        fun getIntent(
            context: Context,
            movieToReserve: MovieToReserve,
        ): Intent =
            Intent(context, SeatSelectionActivity::class.java).apply {
                putExtra(MOVIE_TO_RESERVE_DATA_KEY, movieToReserve)
            }
    }
}
