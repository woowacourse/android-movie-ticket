package woowacourse.movie.seatSelection

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import model.MovieTicketModel
import model.SeatSelectionModel
import woowacourse.movie.R
import woowacourse.movie.movieTicket.MovieTicketActivity
import woowacourse.movie.utils.getSerializableExtraCompat
import woowacourse.movie.utils.keyError
import woowacourse.movie.utils.showAskDialog

class SeatSelectionActivity : AppCompatActivity() {
    private lateinit var seatSelection: SeatSelectionModel
    private lateinit var seatSelectionView: SeatSelectionView

    private fun onConfirmClick(movieTicketModel: MovieTicketModel) = showAskDialog(
        title = getString(R.string.seat_selection_confirm_dialog_title),
        message = getString(R.string.seat_selection_confirm_dialog_contents),
        positiveString = getString(R.string.seat_selection_confirm_dialog_yes),
        negativeString = getString(R.string.seat_selection_confirm_dialog_no),
        actionPositive = { MovieTicketActivity.start(this, movieTicketModel) },
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_selection)

        seatSelection = intent.getSerializableExtraCompat(KEY_SEAT_SELECTION) ?: return keyError(KEY_SEAT_SELECTION)

        initToolbar()

        seatSelectionView = createSeatSelectionView()
        savedInstanceState?.let { seatSelectionView.restoreInstanceState(it) }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        seatSelectionView.saveInstanceState(outState)
    }

    private fun createSeatSelectionView(): SeatSelectionView = SeatSelectionView(
        window.decorView.rootView,
        seatSelection,
    ) { ticketModel -> onConfirmClick(ticketModel) }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initToolbar() {
        val reservationToolbar = findViewById<Toolbar>(R.id.seat_selection_toolbar)
        setSupportActionBar(reservationToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    companion object {
        private const val KEY_SEAT_SELECTION = "key_seat_selection"

        fun <T : Context> getIntent(context: T, seatSelection: SeatSelectionModel) =
            Intent(context, SeatSelectionActivity::class.java).apply {
                putExtra(KEY_SEAT_SELECTION, seatSelection)
            }

        fun <T : Context> start(context: T, seatSelection: SeatSelectionModel) {
            context.startActivity(
                Intent(context, SeatSelectionActivity::class.java).apply {
                    putExtra(KEY_SEAT_SELECTION, seatSelection)
                },
            )
        }
    }
}
