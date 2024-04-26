package woowacourse.movie.presentation.detail

import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.presentation.model.TicketModel
import woowacourse.movie.presentation.reservation.MovieReservationPresenter
import woowacourse.movie.presentation.utils.toCustomString

class TicketDetailActivity : AppCompatActivity(), TicketDetailContract.View {
    private lateinit var ticketTitle: TextView
    private lateinit var ticketScreeningDate: TextView
    private lateinit var ticketPrice: TextView
    private lateinit var ticketCount: TextView
    private lateinit var ticketTime: TextView
    private lateinit var ticketSeat: TextView

    private val presenter: TicketDetailPresenter by lazy {
        TicketDetailPresenter(
            view = this@TicketDetailActivity,
            ticketModel = getReservationTicket(),
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_reservation_complete)
        initView()
        presenter.loadTicket()
    }

    private fun initView() {
        ticketTitle = findViewById(R.id.ticket_title)
        ticketScreeningDate = findViewById(R.id.ticket_screening_date)
        ticketPrice = findViewById(R.id.ticket_price)
        ticketCount = findViewById(R.id.ticket_number_of_people)
        ticketTime = findViewById(R.id.ticket_screening_time)
        ticketSeat = findViewById(R.id.ticket_seat)
    }

    private fun getReservationTicket(): TicketModel {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(
                MovieReservationPresenter.KEY_NAME_TICKET,
                TicketModel::class.java,
            ) ?: TicketModel.defaultTicket
        } else {
            intent.getSerializableExtra(MovieReservationPresenter.KEY_NAME_TICKET) as? TicketModel
                ?: TicketModel.defaultTicket
        }
    }

    override fun showTicket(ticketModel: TicketModel) {
        ticketTitle.text = ticketModel.title
        ticketScreeningDate.text = ticketModel.screeningDate.toCustomString()
        ticketPrice.text = getString(R.string.ticket_price, ticketModel.price)
        ticketCount.text = getString(R.string.ticket_count, ticketModel.count)
        ticketTime.text = ticketModel.screeningTime.toCustomString()
        ticketSeat.text = ticketModel.seats.joinToString(SEAT_SEPARATOR)
    }

    companion object {
        private const val SEAT_SEPARATOR = ", "
    }
}
