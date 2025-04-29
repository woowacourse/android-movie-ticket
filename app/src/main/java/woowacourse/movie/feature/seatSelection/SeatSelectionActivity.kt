package woowacourse.movie.feature.seatSelection

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.children
import woowacourse.movie.R
import woowacourse.movie.feature.model.movie.TicketUiModel
import woowacourse.movie.feature.model.seat.SeatsUiModel
import woowacourse.movie.feature.movieReservation.MovieReservationActivity.Companion.KEY_TICKET
import woowacourse.movie.feature.movieReservationResult.MovieReservationResultActivity
import woowacourse.movie.util.buildAlertDialog
import woowacourse.movie.util.getParcelableCompat

class SeatSelectionActivity : AppCompatActivity(), SeatSelectionContract.View {
    val presenter = SeatSelectionPresenter(this)
    private val seatButtons = mutableListOf<Button>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeView()
        val ticket = intent.extras?.getParcelableCompat<TicketUiModel>(KEY_TICKET) ?: return
        presenter.onViewCreated(ticket)
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

    private fun initializeSeats() {
        val seatsLayout = findViewById<TableLayout>(R.id.seats)
        seatButtons.addAll(
            seatsLayout.children
                .filterIsInstance<TableRow>()
                .flatMap { row -> row.children }
                .filterIsInstance<Button>(),
        )
        seatButtons.forEachIndexed { index, button ->
            button.setOnClickListener {
                presenter.onSeatSelection(index)
            }
        }
    }

    private fun initializeSelectButton() {
        val selectButton = findViewById<Button>(R.id.select_button)
        selectButton.setOnClickListener {
            presenter.onConfirmation()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_SEATS, presenter.seats)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val seats = savedInstanceState.getParcelableCompat<SeatsUiModel>(KEY_SEATS) ?: return
        presenter.onInstanceStateRestored(seats)
    }

    override fun selectSeat(index: Int) {
        seatButtons[index].setBackgroundColor(getColor(R.color.seat_selected))
    }

    override fun deselectSeat(index: Int) {
        seatButtons[index].setBackgroundColor(getColor(R.color.seat_default))
    }

    override fun showMovieTitle(title: String) {
        val titleTextView = findViewById<TextView>(R.id.movie_title)
        titleTextView.text = title
    }

    override fun showTotalPrice(price: Int) {
        val priceTextView = findViewById<TextView>(R.id.total_price)
        priceTextView.text = getString(R.string.template_price).format(price)
    }

    override fun showAlertDialog() {
        val alertDialog =
            buildAlertDialog(
                title = R.string.alert_title_confirm_reservation,
                message = R.string.alert_message_confirm_reservation,
                yes = R.string.alert_confirm_reservation_yes,
            ) { presenter.onAlertConfirmation() }
        alertDialog.show()
    }

    override fun goToReservationResult(
        ticket: TicketUiModel,
        seats: SeatsUiModel,
    ) {
        val intent = MovieReservationResultActivity.createIntent(this, ticket, seats)
        startActivity(intent)
    }

    override fun showSelectionFinishedToast() {
        Toast.makeText(
            this,
            getString(R.string.toast_message_cannot_select_more_seats),
            Toast.LENGTH_SHORT,
        ).show()
    }

    override fun showSelectionNotFinishedToast(required: Int) {
        Toast.makeText(
            this,
            getString(R.string.toast_message_need_to_select_more_seats).format(required),
            Toast.LENGTH_SHORT,
        ).show()
    }

    companion object {
        private const val KEY_SEATS = "seats"

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
