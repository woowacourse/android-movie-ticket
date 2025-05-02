package woowacourse.movie.selectSeat

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.booking.BookingActivity.Companion.KEY_TICKET
import woowacourse.movie.booking.ConfirmDialog
import woowacourse.movie.bookingResult.BookingResultActivity
import woowacourse.movie.uiModel.TicketUIModel
import java.text.DecimalFormat

class SelectSeatActivity :
    AppCompatActivity(),
    SelectSeatContract.View {
    private var presenter: SelectSeatPresenter = SelectSeatPresenter(this)
    lateinit var ticketUIModel: TicketUIModel
    lateinit var button: Button
    lateinit var moneyView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.select_seat)
        ticketUIModel =
            intent.fetchExtraOrNull<TicketUIModel>(KEY_TICKET) ?: return
        button = findViewById<Button>(R.id.seat_select_button)
        moneyView = findViewById<TextView>(R.id.money)
        setTitle()
        setSeatClicker()
        setButton()
        presenter.init(ticketUIModel)
    }

    fun setTitle() {
        val titleView = findViewById<TextView>(R.id.title)
        titleView.text = ticketUIModel.title
    }

    override fun showPrice(money: Int) {
        val formatter = DecimalFormat("#,###")
        val moneyWithComma = formatter.format(money)
        moneyView.text = getString(R.string.price_format, moneyWithComma)
    }

    fun setSeatClicker() {
        val seatSet = findViewById<ViewGroup>(R.id.seat_set)

        for (i in 0 until seatSet.childCount) {
            val row = seatSet.getChildAt(i) as ViewGroup

            for (j in 0 until row.childCount) {
                val seat = row.getChildAt(j) as TextView
                val rowChar = 'A' + i
                val seatNumber = j + 1
                seat.tag = "$rowChar$seatNumber"
                seat.setOnClickListener {
                    presenter.toggleSeat(seat.tag.toString(), ticketUIModel.count)
                }
            }
        }
    }

    fun setButton() {
        val button = findViewById<Button>(R.id.seat_select_button)

        button.setOnClickListener {
            presenter.askConfirm()
        }
    }

    override fun askToConfirmBook() {
        ConfirmDialog.show(this) {
            presenter.completeBooking()
        }
    }

    override fun navigateToBookingResult(ticketUIModel: TicketUIModel) {
        val intent =
            Intent(this, BookingResultActivity::class.java).apply {
                putExtra(KEY_TICKET, ticketUIModel)
            }
        startActivity(intent)
    }

    override fun activeButton() {
        button.setEnabled(true)
    }

    override fun disActiveButton() {
        button.setEnabled(false)
    }

    override fun highlightSeat(tag: String) {
        val viewGroup = findViewById<ViewGroup>(R.id.seat_set)
        viewGroup
            .findViewWithTag<TextView>(tag)
            .setBackgroundColor(resources.getColor(R.color.yellow))
    }

    override fun unHighlightSeat(tag: String) {
        val viewGroup = findViewById<ViewGroup>(R.id.seat_set)
        viewGroup
            .findViewWithTag<TextView>(tag)
            .setBackgroundColor(resources.getColor(R.color.white))
    }

    override fun showFullSeat() {
        Toast.makeText(this, getString(R.string.full_seat), Toast.LENGTH_SHORT).show()
    }

    inline fun <reified T : Parcelable> Intent.fetchExtraOrNull(key: String): T? = getParcelableExtra(key)
}
