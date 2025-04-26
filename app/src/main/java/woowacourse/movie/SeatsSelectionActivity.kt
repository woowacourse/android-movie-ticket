package woowacourse.movie

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import woowacourse.movie.domain.model.MovieTicket
import woowacourse.movie.presenter.SeatsSelectionPresenter
import woowacourse.movie.ui.view.BaseActivity
import woowacourse.movie.ui.view.booking.BookingSummaryActivity
import woowacourse.movie.ui.view.booking.BookingSummaryActivity.Companion.formatAmount
import woowacourse.movie.ui.view.booking.SeatsSelectionContract
import woowacourse.movie.ui.view.seat.SeatButtonState
import woowacourse.movie.ui.view.utils.intentSerializable

class SeatsSelectionActivity :
    BaseActivity(),
    SeatsSelectionContract.View {
    private lateinit var presenter: SeatsSelectionPresenter
    private val selectedColor by lazy { ContextCompat.getColor(this, R.color.yellow) }
    private val defaultColor by lazy { ContextCompat.getColor(this, R.color.white) }
    private val movieTicket: MovieTicket by lazy {
        intent
            .intentSerializable(
                getString(R.string.ticket_info_key),
                MovieTicket::class.java,
            )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupScreen(R.layout.activity_seat_selection)

        presenter = SeatsSelectionPresenter(this, movieTicket)
        presenter.loadMovieTitle()
        presenter.loadAmount()
        setupConfirmButtonListener()
        setupSeatButtonsListener()
    }

    override fun showBookingConfirmDialog() {
        AlertDialog
            .Builder(this)
            .setTitle(getString(R.string.dialog_title))
            .setMessage(getString(R.string.dialog_message))
            .setPositiveButton(getString(R.string.complete)) { _, _ ->
                presenter.onConfirm()
            }.setNegativeButton(getString(R.string.cancel)) { dialog, _ -> dialog.dismiss() }
            .setCancelable(false)
            .show()
    }

    override fun showMovieTitle(movieTitle: String) {
        val movieTitleView = findViewById<TextView>(R.id.movie_title)
        movieTitleView.text = movieTitle
    }

    override fun showAmount(amount: Int) {
        val amountView = findViewById<TextView>(R.id.amount)
        amountView.text = formatAmount(getString(R.string.won), amount)
    }

    override fun navigateToBookingSummary() {
        val movieTicket =
            intent.getSerializableExtra(getString(R.string.ticket_info_key)) as MovieTicket

        val intent = Intent(this, BookingSummaryActivity::class.java)
        intent.putExtra(getString(R.string.ticket_info_key), movieTicket)
        startActivity(intent)
    }

    override fun showSeatLimitToastMessage() {
        Toast
            .makeText(
                this,
                R.string.seat_limit_message,
                Toast.LENGTH_SHORT,
            ).show()
    }

    private fun setupConfirmButtonListener() {
        val confirmBtn = findViewById<Button>(R.id.confirm_button)

        confirmBtn.setOnClickListener {
            showBookingConfirmDialog()
        }
    }

    private fun setupSeatButtonsListener() {
        val buttonIds = createButtonIds()
        buttonIds.forEach { buttonName ->
            val buttonID = resources.getIdentifier(buttonName, ID, packageName)
            val button = findViewById<Button>(buttonID)
            button.setOnClickListener {
                val result = presenter.getSeatResult(button.text.toString())
                when (result) {
                    SeatButtonState.SELECTED -> button.setBackgroundColor(selectedColor)
                    SeatButtonState.DESELECTED -> button.setBackgroundColor(defaultColor)
                    SeatButtonState.LIMIT -> showSeatLimitToastMessage()
                }
                presenter.loadAmount()
            }
        }
    }

    companion object {
        private val rows = listOf("A", "B", "C", "D", "E")
        private val cols = listOf(1, 2, 3, 4)
        private const val ID = "id"
        private const val BUTTON_ID_FORMAT = "button_%s%d"

        private fun createButtonIds() =
            rows
                .flatMap { row ->
                    cols.map { col ->
                        BUTTON_ID_FORMAT.format(row, col)
                    }
                }
    }
}
