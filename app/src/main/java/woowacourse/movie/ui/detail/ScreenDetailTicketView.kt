package woowacourse.movie.ui.detail

import android.app.Activity
import android.widget.Button
import android.widget.TextView
import woowacourse.movie.R

class ScreenDetailTicketView(activity: Activity, private val presenter: ScreenDetailContract.Presenter) : TicketView {
    private val ticketCount: TextView = activity.findViewById(R.id.tv_count)
    private val plusBtn: Button = activity.findViewById(R.id.btn_plus)
    private val minusBtn: Button = activity.findViewById(R.id.btn_minus)
    private val reserveDone: Button = activity.findViewById(R.id.btn_reserve_done)

    override fun initClickListener(screenId: Int) {
        plusBtn.setOnClickListener {
            presenter.plusTicket()
        }
        minusBtn.setOnClickListener {
            presenter.minusTicket()
        }
        reserveDone.setOnClickListener {
            presenter.reserve(screenId)
        }
    }

    override fun updateTicketCount(count: Int) {
        ticketCount.text = count.toString()
    }

    override fun ticketCount(): Int = ticketCount.text.toString().toInt()

    override fun restoreTicketCount(count: Int) {
        ticketCount.text = count.toString()
    }
}
