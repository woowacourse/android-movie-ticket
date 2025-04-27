package woowacourse.movie.view.seatSelection

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.toColorInt
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.children
import woowacourse.movie.R
import woowacourse.movie.presenter.seatSelection.SeatSelectionContract
import woowacourse.movie.presenter.seatSelection.SeatSelectionPresenter
import woowacourse.movie.view.model.TicketUiModel
import woowacourse.movie.view.movieReservationResult.MovieReservationResultActivity
import woowacourse.movie.view.utils.buildAlertDialog

class SeatSelectionActivity : AppCompatActivity(), SeatSelectionContract.View {
    val presenter = SeatSelectionPresenter(this)
    private lateinit var seatButtons: List<Button>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeView()
        presenter.loadReservationInfo()
        initializeSeats()
        initializeSelectButton()
    }

    private fun initializeView() {
        enableEdgeToEdge()
        setContentView(R.layout.activity_movie_seat_selection)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun initializeSeats() {
        val seatsLayout = findViewById<TableLayout>(R.id.seats)
        seatButtons =
            seatsLayout.children
                .filterIsInstance<TableRow>()
                .flatMap { row -> row.children }
                .filterIsInstance<Button>()
                .toList()
        seatButtons.forEachIndexed { index, button ->
            val row = index / 4
            val col = index % 4
            button.setOnClickListener {
                presenter.onSeatSelection(row, col)
            }
        }
    }

    override fun selectSeat(
        row: Int,
        col: Int,
    ) {
        val index = row * 4 + col
        seatButtons[index].setBackgroundColor("#FAFF00".toColorInt())
    }

    override fun deselectSeat(
        row: Int,
        col: Int,
    ) {
        val index = row * 4 + col
        seatButtons[index].setBackgroundColor("#FFFFFF".toColorInt())
    }

    override fun showMovieTitle(title: String) {
        val titleTextView = findViewById<TextView>(R.id.movie_title)
        titleTextView.text = title
    }

    override fun showTotalPrice(price: Int) {
        val priceTextView = findViewById<TextView>(R.id.total_price)
        priceTextView.text = getString(R.string.ticket_price_format).format(price)
    }

    override fun showAlertDialog() {
        val alertDialog =
            buildAlertDialog(
                title = R.string.confirm_reservation_alert_title,
                message = R.string.confirm_reservation_alert_message,
                yes = R.string.confirm_reservation_alert_yes,
            ) { presenter.onSelectionConfirmation() }
        alertDialog.show()
    }

    override fun confirmSelection(ticket: TicketUiModel) {
        val intent = MovieReservationResultActivity.createIntent(this, ticket)
        startActivity(intent)
    }

    private fun initializeSelectButton() {
        val selectButton = findViewById<Button>(R.id.select_button)
        selectButton.setOnClickListener {
            presenter.onSelection()
        }
    }

    companion object {
        const val KEY_TICKET = "ticket"

        fun createIntent(
            context: Context,
            ticket: TicketUiModel,
        ): Intent {
            val intent = Intent(context, SeatSelectionActivity::class.java)
            intent.putExtra(KEY_TICKET, ticket)
            return intent
        }
    }
}
