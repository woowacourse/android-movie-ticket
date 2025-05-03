package woowacourse.movie.ui.view.seat

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.view.children
import woowacourse.movie.R
import woowacourse.movie.domain.model.MovieTicket
import woowacourse.movie.domain.model.ReservedMovie
import woowacourse.movie.presenter.SeatsSelectionPresenter
import woowacourse.movie.ui.view.BaseActivity
import woowacourse.movie.ui.view.booking.BookingSummaryActivity
import woowacourse.movie.ui.view.booking.BookingSummaryActivity.Companion.formatAmount
import woowacourse.movie.ui.view.utils.intentSerializable

class SeatsSelectionActivity :
    BaseActivity(),
    SeatsSelectionContract.View {
    private lateinit var presenter: SeatsSelectionPresenter
    private val selectedColor by lazy { ContextCompat.getColor(this, R.color.yellow) }
    private val defaultColor by lazy { ContextCompat.getColor(this, R.color.white) }
    private lateinit var tableLayout: TableLayout
    private val reservedMovie: ReservedMovie by lazy {
        intent
            .intentSerializable(
                getString(R.string.reserved_movie_info_key),
                ReservedMovie::class.java,
            )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupScreen(R.layout.activity_seat_selection)
        tableLayout = findViewById(R.id.seats)
        presenter = SeatsSelectionPresenter(this, reservedMovie)
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

    override fun navigateToBookingSummary(movieTicket: MovieTicket) {
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

    override fun changeSeatColor(
        row: Int,
        col: Int,
        isSelected: Boolean,
    ) {
        val tableRow = tableLayout.getChildAt(row) as TableRow
        val button = tableRow.getChildAt(col) as Button
        val color = if (isSelected) selectedColor else defaultColor
        button.setBackgroundColor(color)
    }

    private fun setupConfirmButtonListener() {
        val confirmBtn = findViewById<Button>(R.id.confirm_button)

        confirmBtn.setOnClickListener {
            showBookingConfirmDialog()
        }
    }

    private fun setupSeatButtonsListener() {
        tableLayout.children.forEachIndexed { rowIndex, rowView ->
            val tableRow = rowView as TableRow
            tableRow.children.forEachIndexed { colIndex, view ->
                val button = view as Button
                button.setOnClickListener {
                    presenter.onClickSeat(rowIndex, colIndex)
                }
            }
        }
    }
}
