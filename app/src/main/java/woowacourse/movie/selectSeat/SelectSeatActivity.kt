package woowacourse.movie.selectSeat

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.booking.BookingActivity.Companion.KEY_TICKET
import woowacourse.movie.booking.ConfirmDialog
import woowacourse.movie.bookingResult.BookingResultActivity
import woowacourse.movie.dto.Ticket
import java.text.DecimalFormat

class SelectSeatActivity :
    AppCompatActivity(),
    SelectSeatContract.View {
    private var presenter: SelectSeatPresenter = SelectSeatPresenter(this)
    lateinit var ticket: Ticket

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.select_seat)
        ticket =
            intent.fetchExtraOrNull<Ticket>(KEY_TICKET) ?: return
        presenter.onViewCreated(ticket)
    }

    override fun setTitle(ticket: Ticket) {
        val titleView = findViewById<TextView>(R.id.title)
        titleView.text = ticket.title
    }

    override fun setMoney(money: Int) {
        val moneyView = findViewById<TextView>(R.id.money)
        val formatter = DecimalFormat("#,###")
        val moneyWithComma = formatter.format(money)
        moneyView.text = getString(R.string.price_format, moneyWithComma)
    }

    override fun setSeatClicker() {
        val seatSet = findViewById<ViewGroup>(R.id.seat_set)

        for (i in 0 until seatSet.childCount) {
            val row = seatSet.getChildAt(i) as ViewGroup

            for (j in 0 until row.childCount) {
                val seat = row.getChildAt(j) as TextView
                val rowChar = 'A' + i
                val seatNumber = j + 1
                seat.tag = "$rowChar$seatNumber"
                seat.setOnClickListener {
                    presenter.onSeatClicked(seat.tag.toString(), ticket.count)
                }
            }
        }
    }

    override fun setButton() {
        val button = findViewById<Button>(R.id.seat_select_button)

        button.setOnClickListener {
            presenter.onBookButtonClicked()
        }
    }

    override fun askToConfirmBook() {
        ConfirmDialog.show(this) {
            presenter.onYesClick()
        }
    }

    override fun changeView(ticket: Ticket) {
        val intent =
            Intent(this, BookingResultActivity::class.java).apply {
                putExtra(KEY_TICKET, ticket)
            }
        startActivity(intent)
    }

    override fun onSeatSelected(tag: String) {
        val viewGroup = findViewById<ViewGroup>(R.id.seat_set)
        viewGroup.findViewWithTag<TextView>(tag).setBackgroundColor(resources.getColor(R.color.yellow))
    }

    override fun onSeatUnSelected(tag: String) {
        val viewGroup = findViewById<ViewGroup>(R.id.seat_set)
        viewGroup.findViewWithTag<TextView>(tag).setBackgroundColor(resources.getColor(R.color.white))
    }

    override fun showFullSeat() {
        Toast.makeText(this, getString(R.string.full_seat), Toast.LENGTH_SHORT).show()
    }

    inline fun <reified T : Parcelable> Intent.fetchExtraOrNull(key: String): T? = getParcelableExtra(key)

    companion object {
        const val TICKET_KEY = "TICKET"
    }
}
