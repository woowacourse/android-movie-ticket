package woowacourse.movie.presentation.detail

import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.presentation.reservation.MovieReservationPresenter
import woowacourse.movie.presentation.reservation.model.TicketModel
import woowacourse.movie.utils.toCustomString

class TicketDetailActivity : AppCompatActivity(), TicketDetailContract.View {
    private lateinit var ticketTitle: TextView
    private lateinit var ticketScreeningDate: TextView
    private lateinit var ticketPrice: TextView
    private lateinit var ticketCount: TextView
    private val presenter: TicketDetailPresenter by lazy {
        TicketDetailPresenter(
            view = this@TicketDetailActivity,
            ticketModel = getReservationTicket()
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
    }

    private fun getReservationTicket(): TicketModel? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(
                MovieReservationPresenter.KEY_NAME_TICKET,
                TicketModel::class.java
            )
        } else {
            intent.getSerializableExtra(MovieReservationPresenter.KEY_NAME_TICKET) as? TicketModel
        }
    }

    override fun showTicket(ticketModel: TicketModel?) {
        ticketModel ?: return
        ticketTitle.text = ticketModel.title
        ticketScreeningDate.text = ticketModel.screeningDate.toCustomString()
        ticketPrice.text = String.format(TICKET_PRICE, ticketModel.price)
        ticketCount.text = TICKET_COUNT.format(ticketModel.count)
    }

    companion object {
        private const val TICKET_PRICE = "%,d원 (현장결제)"
        private const val TICKET_COUNT = "일반 %d명"
    }
}
