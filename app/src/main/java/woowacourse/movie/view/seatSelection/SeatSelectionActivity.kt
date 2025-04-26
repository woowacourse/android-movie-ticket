package woowacourse.movie.view.seatSelection

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.children
import woowacourse.movie.R
import woowacourse.movie.model.seat.Seat
import woowacourse.movie.model.ticket.MovieTicket
import woowacourse.movie.presenter.seatSelection.SeatSelectionContracts
import woowacourse.movie.presenter.seatSelection.SeatSelectionPresenter
import woowacourse.movie.view.extension.getSerializableExtraData
import woowacourse.movie.view.mapper.Formatter.priceToUI
import woowacourse.movie.view.seatSelection.SeatSelectionFormatter.columnToUI
import woowacourse.movie.view.seatSelection.SeatSelectionFormatter.rowToUI

class SeatSelectionActivity :
    AppCompatActivity(),
    SeatSelectionContracts.View {
    private val presenter: SeatSelectionContracts.Presenter = SeatSelectionPresenter(this)
    private val seatsLayout: TableLayout by lazy { findViewById(R.id.tl_seat_selection) }
    private val titleTextView: TextView by lazy { findViewById(R.id.tv_seat_selection_movie_title) }
    private val seatPriceTextView: TextView by lazy { findViewById(R.id.tv_seat_selection_price) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_selection)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.seat_selection_main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        presenter.updateTicketData(intent.getSerializableExtraData<MovieTicket>(TICKET_DATA_KEY))
    }

    override fun showSeats(seats: List<Seat>) {
        seatsLayout.children
            .filterIsInstance<TableRow>()
            .forEachIndexed { rowIndex, row ->
                row.children
                    .filterIsInstance<TextView>()
                    .forEachIndexed { colIndex, view ->
                        val key = "${rowToUI(rowIndex)}${columnToUI(colIndex)}"
                        view.setTag(R.id.seat_cell_key, key)
                        view.text = key
                        view.setOnClickListener {
                            view.isSelected = !view.isSelected
                            presenter.updateSelectedSeat(rowIndex, colIndex)
                        }
                    }
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

    companion object {
        private const val TICKET_DATA_KEY = "movieTicket"

        fun getIntent(
            context: Context,
            movieTicket: MovieTicket,
        ): Intent =
            Intent(context, SeatSelectionActivity::class.java).apply {
                putExtra(TICKET_DATA_KEY, movieTicket)
            }
    }
}
