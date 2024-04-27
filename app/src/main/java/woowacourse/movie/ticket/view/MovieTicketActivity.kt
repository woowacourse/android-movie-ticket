package woowacourse.movie.ticket.view

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.seats.model.Seat
import woowacourse.movie.seats.view.SeatsActivity.Companion.DATE_KEY
import woowacourse.movie.seats.view.SeatsActivity.Companion.ID_KEY
import woowacourse.movie.seats.view.SeatsActivity.Companion.PRICE_KEY
import woowacourse.movie.seats.view.SeatsActivity.Companion.SEATS_KEY
import woowacourse.movie.seats.view.SeatsActivity.Companion.TIME_KEY
import woowacourse.movie.ticket.contract.MovieTicketContract
import woowacourse.movie.ticket.presenter.MovieTicketPresenter
import woowacourse.movie.util.IntentUtil.getSerializableCountData

class MovieTicketActivity : AppCompatActivity(), MovieTicketContract.View {
    private lateinit var title: TextView
    private lateinit var screeningDate: TextView
    private lateinit var screeningTime: TextView
    private lateinit var price: TextView
    private lateinit var count: TextView
    private lateinit var seats: TextView
    override val presenter: MovieTicketPresenter = MovieTicketPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_ticket)
        initViewById()
        processPresenterTask()
    }

    private fun processPresenterTask() {
        presenter.storeTicketCount(getSerializableCountData(intent))
        presenter.storeMovieId(intent.getLongExtra(ID_KEY, -1))
        presenter.storeScreeningDate(intent.getStringExtra(DATE_KEY) ?: "ddd")
        presenter.storeScreeningTime(intent.getStringExtra(TIME_KEY) ?: "ddd")
        presenter.storePrice(intent.getIntExtra(PRICE_KEY, 0))
        presenter.storeSeats(intent.getSerializableExtra(SEATS_KEY) as List<Seat>)
        presenter.setScreeningDateInfo()
        presenter.setScreeningTimeInfo()
        presenter.setSeatsInfo()
        presenter.setTicketInfo()
    }

    override fun showSeats(seats: List<Seat>) {
        val seatsCoordinate = seats.map { it.coordinate }
        this.seats.text = seatsCoordinate.joinToString()
    }

    private fun initViewById() {
        title = findViewById(R.id.ticket_title)
        screeningDate = findViewById(R.id.ticket_screening_date)
        screeningTime = findViewById(R.id.ticket_screening_time)
        price = findViewById(R.id.ticket_price)
        count = findViewById(R.id.ticket_number_of_people)
        seats = findViewById(R.id.seats)
    }

    override fun showTicketView(
        movieTitle: String,
        moviePrice: Int,
        ticketCount: Int,
    ) {
        title.text = movieTitle
        price.text = TICKET_PRICE.format(moviePrice)
        count.text = TICKET_COUNT.format(ticketCount)
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
    }
}
