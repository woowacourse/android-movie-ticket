package woowacourse.movie.view.seatselection

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.core.view.children
import com.example.domain.Ticket
import com.example.domain.seat.indexToPosition
import woowacourse.movie.R
import woowacourse.movie.view.BaseActivity
import woowacourse.movie.view.TicketActivity
import woowacourse.movie.view.viewmodel.MovieUIModel
import woowacourse.movie.view.viewmodel.toUIModel
import java.time.LocalDateTime

class SeatSelectionActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_selection)
        setBackToBefore()

        val selectedDate = intent.getSerializableExtra(DATE_KEY) as LocalDateTime
        val selectedNumberOfPeople = intent.getIntExtra(NUMBER_OF_PEOPLE_KEY, 0)
        val movieUI = intent.getSerializableExtra(MOVIE_KEY) as MovieUIModel

        findViewById<TextView>(R.id.seat_selection_title).text = movieUI.title
        val price = findViewById<TextView>(R.id.seat_selection_price)
        val goToTicketBtn = findViewById<Button>(R.id.seat_selection_check)
        val seatsView = findViewById<TableLayout>(R.id.seat_selection)
            .children
            .filterIsInstance<TableRow>()
            .flatMap { it.children }
            .filterIsInstance<TextView>().toList()

        price.text = this.getString(R.string.price, 0)
        val seatState = SeatState(selectedDate)

        seatsView.forEachIndexed { index, view ->
            view.setOnClickListener {
                seatState.setSeatState(index, view, this)
                price.text = getString(R.string.price, seatState.priceNum)

                goToTicketBtn.isEnabled = (selectedNumberOfPeople == seatState.countPeople)
            }
        }

        goToTicketBtn.setOnClickListener {
            val seats = seatsView.withIndex().filter { it.value.isSelected }.map { indexToPosition(it.index) }
            val ticket = Ticket(seatState.priceNum, selectedDate, seatState.countPeople, seats)
            checkAgain(ticket, movieUI)
        }
    }

    private fun checkAgain(ticket: Ticket, movieUI: MovieUIModel) {
        AlertDialog.Builder(this)
            .setTitle(RESERVATION_CHECK)
            .setMessage(ARE_YOU_SURE)
            .setPositiveButton(RESERVATION_COMPLETE) { _, _ ->
                val intent = Intent(this, TicketActivity::class.java)
                intent.putExtra(MOVIE_KEY, movieUI)
                intent.putExtra(TICKET_KEY, ticket.toUIModel())
                startActivity(intent)
            }
            .setNegativeButton(RESERVATION_CANCEL) { dialog, _ ->
                dialog.dismiss()
            }
            .setCancelable(false)
            .show()
    }

    companion object {
        private const val MOVIE_KEY = "movie"
        private const val DATE_KEY = "date"
        private const val TICKET_KEY = "ticket"
        private const val NUMBER_OF_PEOPLE_KEY = "numberOfPeople"
        private const val RESERVATION_CHECK = "예매 확인"
        private const val ARE_YOU_SURE = "정말 예매하시겠습니까?"
        private const val RESERVATION_COMPLETE = "예매 완료"
        private const val RESERVATION_CANCEL = "취소"
    }
}
