package woowacourse.movie.booking.complete

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.util.parcelableExtraWithVersion

class BookingCompleteActivity :
    AppCompatActivity(),
    BookingCompleteContract.View {
    private lateinit var presenter: BookingCompleteContract.Presenter
    private lateinit var ticketInfo: BookingCompleteUiModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupView()
        ticketInfo =
            intent.parcelableExtraWithVersion(TICKET_INFO_KEY, BookingCompleteUiModel::class.java)
                ?: return finish()

        presenter = BookingCompletePretender(this)

        presenter.loadTicketInfo(ticketInfo)
    }

    override fun showTicketInfo(uiModel: BookingCompleteUiModel) {
        findViewById<TextView>(R.id.tv_booking_complete_movie_title).text = uiModel.title
        findViewById<TextView>(R.id.tv_booking_complete_movie_date_time).text =
            uiModel.formattedDateTme
        findViewById<TextView>(R.id.tv_booking_complete_ticket_count).text =
            uiModel.formattedQuantity
        findViewById<TextView>(R.id.booking_complete_seat_textview).text = uiModel.formattedSeats
        findViewById<TextView>(R.id.tv_booking_complete_ticket_total_price).text =
            uiModel.formattedPrice
    }

    private fun setupView() {
        enableEdgeToEdge()
        setContentView(R.layout.activity_booking_complete)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_booking_complete)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val TICKET_INFO_KEY = "ticket_info"

        fun newIntent(
            context: Context,
            uiModel: BookingCompleteUiModel,
        ): Intent =
            Intent(context, BookingCompleteActivity::class.java).apply {
                putExtra(TICKET_INFO_KEY, uiModel)
            }
    }
}
