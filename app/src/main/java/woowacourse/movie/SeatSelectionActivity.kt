package woowacourse.movie

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import woowacourse.movie.BookingCompleteActivity.Companion.KEY_BOOKING_RESULT
import woowacourse.movie.booking.detail.TicketUiModel
import woowacourse.movie.mapper.IntentCompat
import woowacourse.movie.seat.SeatSelectionContract
import woowacourse.movie.seat.SeatSelectionPresenter

class SeatSelectionActivity : AppCompatActivity(), SeatSelectionContract.View {
    private lateinit var presenter: SeatSelectionContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_seat_selection)
        setUi()

        val ticket = requireResultOrFinish()
        presenter = SeatSelectionPresenter(this, ticket)
        presenter.initializeData(savedInstanceState)

        setupSeatClickListeners()
        setupConfirmButton()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setUi() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun requireResultOrFinish(): TicketUiModel? {
        return IntentCompat.getParcelableExtra(
            intent,
            KEY_TICKET,
            TicketUiModel::class.java,
        )
            ?: run {
                Log.e(TAG, "인텐트에 영화 예매 정보(KEY_TICKET)가 없습니다.")
                showToastErrorAndFinish(getString(R.string.booking_toast_message))
                null
            }
    }

    private fun setupSeatClickListeners() {
        val tableLayout = findViewById<TableLayout>(R.id.seat_table)

        for (i in 0 until tableLayout.childCount) {
            val row = tableLayout.getChildAt(i)
            if (row is TableRow) {
                for (j in 0 until row.childCount) {
                    val seatView = row.getChildAt(j)
                    if (seatView is TextView) {
                        seatView.setOnClickListener {
                            presenter.onSeatClicked(seatView)
                        }
                    }
                }
            }
        }
    }

    override fun showTicket(ticketUiData: TicketUiModel) {
        val ticketMovieTitle = findViewById<TextView>(R.id.tv_seat_movie_title)
        val ticketAmount = findViewById<TextView>(R.id.tv_seat_amount)

        val totalPriceText =
            getString(R.string.screening_selection_booking_amount, ticketUiData.totalPrice)

        ticketMovieTitle.text = ticketUiData.title
        ticketAmount.text = totalPriceText
    }

    override fun showSeatState(
        seat: TextView,
        isSelected: Boolean,
    ) {
        val colorRes =
            if (isSelected) R.color.seat_selected_background else R.color.seat_unselected_background
        seat.setBackgroundColor(ContextCompat.getColor(this, colorRes))
    }

    override fun showToastErrorAndFinish(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun setButtonEnabled(enabled: Boolean) {
        val confirmButton = findViewById<TextView>(R.id.btn_booking_confirm)
        val colorRes =
            if (enabled) R.color.btn_activate_background else R.color.btn_deactivate_background
        confirmButton.setBackgroundColor(ContextCompat.getColor(this, colorRes))
    }

    private fun setupConfirmButton() {
        val confirmButton = findViewById<TextView>(R.id.btn_booking_confirm)
        confirmButton.setOnClickListener {
            presenter.onButtonClicked()
        }
    }

    override fun showBookingAlertDialog(result: TicketUiModel) {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.dig_title))
            .setMessage(getString(R.string.dig_message))
            .setPositiveButton(getString(R.string.dig_btn_positive_message)) { _, _ ->
                startBookingCompleteActivity(result)
            }
            .setNegativeButton(getString(R.string.dig_btn_negative_message)) { dialog, _ ->
                dialog.dismiss()
            }
            .setCancelable(false)
            .show()
    }

    private fun startBookingCompleteActivity(result: TicketUiModel) {
        val intent =
            Intent(this, BookingCompleteActivity::class.java).apply {
                putExtra(KEY_BOOKING_RESULT, result)
            }
        startActivity(intent)
    }

    override fun getContext(): Context = this

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    companion object {
        private const val TAG = "SeatSelectionActivity"
        const val KEY_TICKET = "ticketUiData"
    }
}
