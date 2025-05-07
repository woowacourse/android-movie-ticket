package woowacourse.movie.feature.ticket

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.extension.getParcelableExtraCompat
import java.time.LocalDateTime

class TicketActivity :
    AppCompatActivity(),
    TicketContract.View {
    private lateinit var present: TicketContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ticket)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.layout_ticket)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        present = TicketPresenter(this, ticketData = getTicketData() ?: return)
        present.initTicketView()
    }

    private fun getTicketData(): TicketData? {
        val ticketData =
            intent.getParcelableExtraCompat<TicketData>(EXTRA_TICKET_DATA)
        if (ticketData == null) {
            printError(getString(R.string.ticket_error_not_receive_data))
            finish()
            return null
        }
        return ticketData
    }

    override fun printError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun setCancelableMinute(cancelableMinute: Int) {
        val cancelableMinuteView = findViewById<TextView>(R.id.tv_ticket_cancel_time_info)
        cancelableMinuteView.text =
            getString(R.string.ticket_cancelable_minute_info, cancelableMinute)
    }

    override fun setMovieTitle(movieTitle: String) {
        val titleView = findViewById<TextView>(R.id.tv_ticket_movie_title)
        titleView.text = movieTitle
    }

    override fun setShowTime(showtime: LocalDateTime) {
        val showtimeView = findViewById<TextView>(R.id.tv_ticket_screening_date)
        showtimeView.text =
            showtime.run {
                getString(R.string.ticket_showtime, year, monthValue, dayOfMonth, hour, minute)
            }
    }

    override fun setTicketCount(ticketCount: Int) {
        val ticketCountView = findViewById<TextView>(R.id.tv_ticket_count)
        ticketCountView.text = getString(R.string.ticket_count, ticketCount)
    }

    override fun setSeatCodes(seatCodes: List<String>) {
        val seatCodeView = findViewById<TextView>(R.id.tv_seat_code)
        seatCodeView.text =
            getString(
                R.string.ticket_seat_code,
                seatCodes.joinToString(),
            )
    }

    override fun setTicketPrice(totalTicketPrice: Int) {
        val priceView = findViewById<TextView>(R.id.tv_ticket_price)
        priceView.text = getString(R.string.ticket_price, totalTicketPrice)
    }

    companion object {
        private const val EXTRA_TICKET_DATA = "woowacourse.movie.EXTRA_TICKET_DATA"

        fun newIntent(
            context: Context,
            ticketData: TicketData,
        ): Intent =
            Intent(context, TicketActivity::class.java).apply {
                putExtra(EXTRA_TICKET_DATA, ticketData)
            }
    }
}
