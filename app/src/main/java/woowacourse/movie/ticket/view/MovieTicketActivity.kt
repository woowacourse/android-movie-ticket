package woowacourse.movie.ticket.view

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.reservation.view.MovieReservationActivity.Companion.EXTRA_COUNT_KEY
import woowacourse.movie.ticket.contract.MovieTicketContract
import woowacourse.movie.ticket.model.Ticket
import woowacourse.movie.ticket.presenter.MovieTicketPresenter
import java.time.format.DateTimeFormatter

class MovieTicketActivity : AppCompatActivity(), MovieTicketContract.View {
    private lateinit var ticketTitle: TextView
    private lateinit var ticketScreeningDate: TextView
    private lateinit var ticketPrice: TextView
    private lateinit var ticketCount: TextView
    override val presenter: MovieTicketPresenter = MovieTicketPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_ticket)
        initViewById()
        presenter.setTicketInfo()
    }

    private fun initViewById() {
        ticketTitle = findViewById(R.id.ticket_title)
        ticketScreeningDate = findViewById(R.id.ticket_screening_date)
        ticketPrice = findViewById(R.id.ticket_price)
        ticketCount = findViewById(R.id.ticket_number_of_people)
    }

    override fun showTicketInfo(info: Ticket) {
        val formattedScreeningDate =
            info.screeningDate.format(DateTimeFormatter.ofPattern(DATE_PATTERN))
        val ticketCountData = intent.getIntExtra(EXTRA_COUNT_KEY, 1)

        ticketTitle.text = info.title
        ticketScreeningDate.text = formattedScreeningDate
        ticketPrice.text = TICKET_PRICE.format(info.price * ticketCountData)
        ticketCount.text = TICKET_COUNT.format(ticketCountData)
    }

    companion object {
        private const val TICKET_PRICE = "%,d원 (현장결제)"
        private const val TICKET_COUNT = "일반 %d명"
        private const val DATE_PATTERN = "yyyy.MM.dd"
    }
}
