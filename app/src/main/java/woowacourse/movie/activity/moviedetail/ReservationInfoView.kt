package woowacourse.movie.activity.moviedetail

import android.content.Intent
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.activity.ticketresult.TicketResultActivity
import java.time.LocalDate
import java.time.LocalTime

class ReservationInfoView(private val viewGroup: ViewGroup) {
    fun setReserveButton(title: String) {
        viewGroup.findViewById<Button>(R.id.btn_reserve).setOnClickListener {
            val intent = Intent(it.context, TicketResultActivity::class.java)
            val ticketingInfo = woowacourse.movie.domain.TicketingInfo.of(
                woowacourse.movie.domain.policy.DiscountPolicies.policies,
                title,
                viewGroup.findViewById<Spinner>(R.id.spinner_date).selectedItem as LocalDate,
                viewGroup.findViewById<Spinner>(R.id.spinner_time).selectedItem as LocalTime,
                viewGroup.findViewById<TextView>(R.id.text_count).text.toString().toInt(),
                woowacourse.movie.domain.Price(),
                "현장"
            )
            intent.putExtra(TicketResultActivity.INFO_KEY, ticketingInfo)
            it.context.startActivity(intent)
        }
    }

    fun setTimeSpinner(savedTimePosition: Int, times: List<LocalTime>) {
        val timeSpinner = viewGroup.findViewById<Spinner>(R.id.spinner_time)
        timeSpinner.adapter =
            ArrayAdapter(viewGroup.context, android.R.layout.simple_spinner_item, times).apply {
                setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }
        timeSpinner.setSelection(savedTimePosition)
    }

    fun setDateSpinner(savedDatePosition: Int, playingTimes: Map<LocalDate, List<LocalTime>>) {
        val dateSpinner = viewGroup.findViewById<Spinner>(R.id.spinner_date)
        val timeSpinner = viewGroup.findViewById<Spinner>(R.id.spinner_time)
        dateSpinner.adapter =
            ArrayAdapter(viewGroup.context, android.R.layout.simple_spinner_item, playingTimes.keys.sorted()).apply {
                setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }
        dateSpinner.setSelection(savedDatePosition, false)
        dateSpinner.onItemSelectedListener = DateSpinnerListener(playingTimes, timeSpinner)
    }

    fun setMinusButton() {
        val minusButton = viewGroup.findViewById<Button>(R.id.btn_minus)
        val countView = viewGroup.findViewById<TextView>(R.id.text_count)
        minusButton.setOnClickListener {
            val count = countView.text.toString().toInt()
            if (count > 1) countView.text = (count - 1).toString()
        }
    }

    fun setPlusButton() {
        val plusButton = viewGroup.findViewById<Button>(R.id.btn_plus)
        val countView = viewGroup.findViewById<TextView>(R.id.text_count)
        plusButton.setOnClickListener {
            val count = countView.text.toString().toInt()
            countView.text = (count + 1).toString()
        }
    }

    fun setCount(savedCount: Int) {
        val countView = viewGroup.findViewById<TextView>(R.id.text_count)
        countView.text = savedCount.toString()
    }
}
