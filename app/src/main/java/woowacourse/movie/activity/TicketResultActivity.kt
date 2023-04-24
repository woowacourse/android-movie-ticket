package woowacourse.movie.activity

import android.os.Bundle
import woowacourse.movie.R
import woowacourse.movie.model.TicketModel
import woowacourse.movie.util.customGetSerializable

class TicketResultActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket_result)

        initTicketDataView()
        setActionBar()
    }

    private fun initTicketDataView() {
        val ticketModel: TicketModel = intent.customGetSerializable(TICKET_KEY)
        InitView.initTextView(findViewById(R.id.text_title), ticketModel.reservationInfoModel.title)
        InitView.initTextView(
            findViewById(R.id.text_playing_date),
            getString(
                R.string.date_time,
                ticketModel.reservationInfoModel.playingDate,
                ticketModel.reservationInfoModel.playingTime
            )
        )
        InitView.initTextView(
            findViewById(R.id.text_count_seat),
            getString(
                R.string.count_seat_info,
                ticketModel.reservationInfoModel.count,
                ticketModel.seats.joinToString(", ") { it.row + it.column }
            )
        )
        InitView.initTextView(
            findViewById(R.id.text_price_payment),
            getString(
                R.string.price_payment,
                ticketModel.price,
                ticketModel.reservationInfoModel.payment
            )
        )
    }

    companion object {
        private const val TICKET_KEY = "ticket"
    }
}
