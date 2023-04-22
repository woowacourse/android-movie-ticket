package woowacourse.movie.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import domain.Ticket
import domain.TicketOffice
import woowacourse.movie.R
import woowacourse.movie.getSerializableCompat
import woowacourse.movie.setBackgroundColorId
import woowacourse.movie.view.MovieView
import woowacourse.movie.view.SeatTable
import woowacourse.movie.view.SeatView
import woowacourse.movie.view.mapper.TicketOfficeMapper
import woowacourse.movie.view.model.MovieViewModel
import woowacourse.movie.view.model.TicketDateTimeViewModel
import java.text.NumberFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

class SelectSeatActivity : AppCompatActivity() {
    private val ticketOffice: TicketOffice by lazy {
        receiveTicketOfficeData()
    }

    private val ticketDateTime: LocalDateTime by lazy {
        receiveTicketDateTimeData() ?: run {
            finishActivityWithToast(DATA_ERROR_MESSAGE)
            LocalDateTime.now()
        }
    }

    private val movieViewModel: MovieViewModel by lazy {
        receiveMovieViewModelData() ?: run {
            finishActivityWithToast(DATA_ERROR_MESSAGE)
            MovieViewModel(0, "", LocalDate.MAX, LocalDate.MAX, 0, "")
        }
    }

    private val seatTable: SeatTable by lazy {
        SeatTable(
            tableLayout = findViewById(R.id.select_seat_tableLayout),
            rowSize = 5,
            colSize = 4,
            ::updateUi
        )
    }

    private val priceTextView: TextView by lazy {
        findViewById(R.id.select_seat_price_text_view)
    }

    private val checkButton: Button by lazy {
        findViewById(R.id.select_seat_check_button)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_seat)
        seatTable.makeSeatTable()
        MovieView(title = findViewById(R.id.select_seat_movie_title_text_view)).render(
            movieViewModel
        )
        changePriceTextView()
        checkButtonClick()
    }

    private fun checkButtonClick() {
        checkButton.setOnClickListener {
            val dialog = createReservationAlertDialog()
            dialog.setCanceledOnTouchOutside(false)
            dialog.show()
        }
        checkButton.isClickable = false
    }

    private fun createReservationAlertDialog(): AlertDialog {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.select_seat_dialog_title)
        builder.setMessage(R.string.select_seat_dialog_message)
        builder.setPositiveButton(R.string.select_seat_dialog_positive_button_text) { dialog, _ ->
            ReservationResultActivity.start(
                this,
                movieViewModel,
                TicketOfficeMapper.toView(ticketOffice)
            )
        }
        builder.setNegativeButton(R.string.select_seat_dialog_negative_button_text) { dialog, _ ->
            dialog.dismiss()
        }
        return builder.create()
    }

    private fun updateUi(seatView: SeatView) {
        changeSeatViewState(seatView)
        changePriceTextView()
        changeCheckButtonState()
    }

    private fun changeSeatViewState(seatView: SeatView) {
        val ticket =
            ticketOffice.generateTicket(ticketDateTime, seatView.seat.row, seatView.seat.col)
        if (ticketOffice.tickets.isContainSameTicket(ticket)) {
            setViewNotSelected(seatView, ticket)
        } else {
            setViewSelected(seatView, ticket)
        }
    }

    private fun setViewNotSelected(seatView: SeatView, ticket: Ticket) {
        ticketOffice.deleteTicket(ticket)
        seatView.view.isSelected = false
        seatView.setBackgroundColorId(color = R.color.white)
    }

    private fun setViewSelected(seatView: SeatView, ticket: Ticket) {
        if (ticketOffice.isAvailableAddTicket()) {
            ticketOffice.addTicket(ticket)
            seatView.view.isSelected = true
            seatView.setBackgroundColorId(color = R.color.seat_selection_selected_seat_color)
        }
    }

    private fun changePriceTextView() {
        val formattedPrice =
            NumberFormat.getNumberInstance(Locale.US).format(ticketOffice.tickets.price.value)
        priceTextView.text = getString(R.string.select_seat_price_format).format(formattedPrice)
    }

    private fun changeCheckButtonState() {
        if (!ticketOffice.isAvailableAddTicket()) {
            checkButton.setBackgroundColorId(R.color.select_seat_clickable_check_button_background)
            checkButton.isClickable = true
        } else {
            checkButton.setBackgroundColorId(R.color.select_seat_non_clickable_check_button_background)
            checkButton.isClickable = false
        }
    }

    private fun finishActivityWithToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG)
        finish()
    }

    private fun receiveTicketOfficeData(): TicketOffice {
        val peopleCount = intent.getIntExtra(PEOPLE_COUNT_KEY, 0)
        if (peopleCount == 0) finishActivityWithToast(DATA_ERROR_MESSAGE)
        return TicketOffice(peopleCount = peopleCount)
    }

    private fun receiveMovieViewModelData(): MovieViewModel? {
        return intent.extras?.getSerializableCompat(MOVIE_KEY_VALUE)
    }

    private fun receiveTicketDateTimeData(): LocalDateTime? {
        return intent.extras?.getSerializableCompat<TicketDateTimeViewModel>(TICKET_KEY)?.date
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("wooseok", "실")
        outState.putSerializable("ticketOffice", TicketOfficeMapper.toView(ticketOffice))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private const val PEOPLE_COUNT_KEY = "peopleCount"
        private const val TICKET_KEY = "ticket"
        private const val MOVIE_KEY_VALUE = "movie"
        private const val DATA_ERROR_MESSAGE = "유효한 데이터를 받지 못했습니다"
        fun start(
            context: Context,
            peopleCount: Int,
            ticketDateTimeViewModel: TicketDateTimeViewModel,
            movieViewModel: MovieViewModel
        ) {
            val intent = Intent(context, SelectSeatActivity::class.java)
            intent.putExtra(PEOPLE_COUNT_KEY, peopleCount)
            intent.putExtra(TICKET_KEY, ticketDateTimeViewModel)
            intent.putExtra(MOVIE_KEY_VALUE, movieViewModel)
            context.startActivity(intent)
        }
    }
}
