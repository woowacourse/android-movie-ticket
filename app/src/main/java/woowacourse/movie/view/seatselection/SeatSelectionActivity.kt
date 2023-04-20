package woowacourse.movie.view.seatselection

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.core.view.children
import indexToPosition
import woowacourse.movie.R
import woowacourse.movie.domain.Ticket
import woowacourse.movie.view.BaseActivity
import woowacourse.movie.view.TicketActivity
import woowacourse.movie.view.viewmodel.MovieUIModel
import woowacourse.movie.view.viewmodel.toUIModel
import java.time.LocalDateTime

class SeatSelectionActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_selection)

        val selectedDate = intent.getSerializableExtra(DATE_KEY) as LocalDateTime
        val selectedNumberOfPeople = intent.getIntExtra(NUMBER_OF_PEOPLE_KEY, 0)
        val movieUI = intent.getSerializableExtra(MOVIE_KEY) as MovieUIModel

        setBackToBefore(R.id.seat_selection_toolbar)

        findViewById<TextView>(R.id.seat_selection_title).text = movieUI.title
        val price = findViewById<TextView>(R.id.seat_selection_price)
        price.text = this.getString(R.string.price, 0)
        val nextBtn = findViewById<Button>(R.id.seat_selection_check)
        val seatsView = findViewById<TableLayout>(R.id.seat_selection)
            .children
            .filterIsInstance<TableRow>()
            .flatMap { it.children }
            .filterIsInstance<TextView>().toList()

        val seatState = SeatState(selectedDate)
        seatsView.forEachIndexed { index, view ->
            view.setOnClickListener {
                seatState.setSeatState(index, view, this)
                price.text = getString(R.string.price, seatState.priceNum)

                nextBtn.isEnabled = (selectedNumberOfPeople == seatState.countPeople)
            }
        }
        nextBtn.setOnClickListener {

            val seats = mutableListOf<Int>()
            seatsView.forEachIndexed { index, view ->
                if (view.isSelected) seats.add(index)
            }
            val ticket = Ticket(
                seatState.priceNum,
                selectedDate,
                seatState.countPeople,
                seats.map { indexToPosition(it) }
            )

            AlertDialog.Builder(this)
                .setTitle("예매 확인")
                .setMessage("정말 예매하시겠습니까?")
                .setPositiveButton("예매 완료") { _, _ ->
                    val intent = Intent(this, TicketActivity::class.java)
                    intent.putExtra(MOVIE_KEY, movieUI)
                    intent.putExtra(TICKET_KEY, ticket.toUIModel())
                    startActivity(intent)
                }
                .setNegativeButton("취소") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }

    companion object {
        private const val MOVIE_KEY = "movie"
        private const val DATE_KEY = "date"
        private const val TICKET_KEY = "ticket"
        private const val NUMBER_OF_PEOPLE_KEY = "numberOfPeople"
    }
}
