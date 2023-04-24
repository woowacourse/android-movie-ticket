package woowacourse.movie.view.seatselection

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.core.view.children
import com.example.domain.Ticket
import com.example.domain.seat.SeatInfo
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

        price.text = this.getString(R.string.price, 0)
        val seatInfo = SeatInfo(selectedDate)
        setSeatView(seatInfo, price, goToTicketBtn, selectedNumberOfPeople)
        clickCheckButton(goToTicketBtn, seatInfo, selectedDate, movieUI)
    }

    private fun clickCheckButton(
        goToTicketBtn: Button,
        seatInfo: SeatInfo,
        selectedDate: LocalDateTime,
        movieUI: MovieUIModel
    ) {
        goToTicketBtn.setOnClickListener {
            val seats = seatInfo.getSelectedSeats()
            val ticket = Ticket(seatInfo.priceNum, selectedDate, seatInfo.countPeople, seats)
            checkAgain(ticket, movieUI)
        }
    }

    private fun setSeatView(
        seatState: SeatInfo,
        price: TextView,
        goToTicketBtn: Button,
        selectedNumberOfPeople: Int
    ) {
        val seatsView = findViewById<TableLayout>(R.id.seat_selection)
            .children
            .filterIsInstance<TableRow>()
            .flatMap { it.children }
            .filterIsInstance<TextView>().toList()

        seatsView.forEachIndexed { index, view ->
            view.setOnClickListener {
                val isSelected = seatState.setSeatState(index)
                view.setBackgroundColor(getColorSeat(isSelected))
                price.text = getString(R.string.price, seatState.priceNum)
                goToTicketBtn.isEnabled = (selectedNumberOfPeople == seatState.countPeople)
            }
        }
    }

    private fun getColorSeat(isSelected: Boolean): Int {
        if (isSelected)
            return getColor(R.color.seat_select_state)
        return getColor(R.color.white)
    }

    private fun checkAgain(ticket: Ticket, movieUI: MovieUIModel) {
        AlertDialog.Builder(this)
            .setTitle(RESERVATION_CHECK)
            .setMessage(ARE_YOU_SURE)
            .setPositiveButton(RESERVATION_COMPLETE) { _, _ ->
                val intent = TicketActivity.newIntent(this, ticket.toUIModel(), movieUI)
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
        private const val NUMBER_OF_PEOPLE_KEY = "numberOfPeople"
        private const val RESERVATION_CHECK = "예매 확인"
        private const val ARE_YOU_SURE = "정말 예매하시겠습니까?"
        private const val RESERVATION_COMPLETE = "예매 완료"
        private const val RESERVATION_CANCEL = "취소"

        fun newIntent(
            context: Context,
            movie: MovieUIModel,
            selectedDateTime: LocalDateTime,
            numberOfPeople: Int
        ): Intent {
            val intent = Intent(context, SeatSelectionActivity::class.java)
            intent.putExtra(DATE_KEY, selectedDateTime)
            intent.putExtra(NUMBER_OF_PEOPLE_KEY, numberOfPeople)
            intent.putExtra(MOVIE_KEY, movie)
            return intent
        }
    }
}
