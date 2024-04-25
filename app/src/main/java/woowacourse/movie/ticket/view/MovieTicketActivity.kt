package woowacourse.movie.ticket.view

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.reservation.model.Count
import woowacourse.movie.reservation.view.MovieReservationActivity.Companion.EXTRA_DATE_KEY
import woowacourse.movie.reservation.view.MovieReservationActivity.Companion.EXTRA_TIME_KEY
import woowacourse.movie.ticket.contract.MovieTicketContract
import woowacourse.movie.ticket.model.Ticket
import woowacourse.movie.ticket.presenter.MovieTicketPresenter
import woowacourse.movie.util.IntentUtil.getSerializableCountData

class MovieTicketActivity : AppCompatActivity(), MovieTicketContract.View {
    private lateinit var title: TextView
    private lateinit var screeningDate: TextView
    private lateinit var screeningTime: TextView
    private lateinit var price: TextView
    private lateinit var count: TextView
    override val presenter: MovieTicketPresenter = MovieTicketPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_ticket)
        initViewById()
        presenter.storeTicketCount(getSerializableCountData(intent))
        presenter.storeScreeningDate(intent.getStringExtra(EXTRA_DATE_KEY) ?: "")
        presenter.storeScreeningTime(intent.getStringExtra(EXTRA_TIME_KEY) ?: "")
        presenter.setTicketInfo()
        presenter.setScreeningDateInfo()
        presenter.setScreeningTimeInfo()
    }

    private fun initViewById() {
        title = findViewById(R.id.ticket_title)
        screeningDate = findViewById(R.id.ticket_screening_date)
        screeningTime = findViewById(R.id.ticket_screening_time)
        price = findViewById(R.id.ticket_price)
        count = findViewById(R.id.ticket_number_of_people)
    }

    override fun showTicketView(
        info: Ticket,
        ticketCount: Count,
    ) {
        title.text = info.title
        price.text = TICKET_PRICE.format(info.price * ticketCount.number)
        count.text = TICKET_COUNT.format(ticketCount.number)
    }

    override fun showScreeningDate(info: String) {
        screeningDate.text = info
    }

    override fun showScreeningTime(info: String) {
        screeningTime.text = info
    }

    companion object {
        private const val TICKET_PRICE = "%,d원 (현장결제)"
        private const val TICKET_COUNT = "일반 %d명"
        private const val DATE_PATTERN = "yyyy.MM.dd"
    }
}
