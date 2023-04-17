package woowacourse.movie.activity.moviedetail

import android.content.Intent
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import woowacourse.movie.activity.ticketresult.TicketResultActivity
import woowacourse.movie.domain.Price
import woowacourse.movie.domain.TicketingInfo
import woowacourse.movie.domain.movie.MovieDTO
import woowacourse.movie.domain.policy.DiscountPolicies
import java.time.LocalDate
import java.time.LocalTime

class ReservationInfoViewInitializer(private val movieDTO: MovieDTO) {
    fun initReserveButton(reserveButton: Button, countView: TextView, dateSpinner: Spinner, timeSpinner: Spinner) {
        reserveButton.setOnClickListener {
            val intent = Intent(it.context, TicketResultActivity::class.java)
            val ticketingInfo = TicketingInfo.of(
                DiscountPolicies.policies,
                movieDTO.title,
                dateSpinner.selectedItem as LocalDate,
                timeSpinner.selectedItem as LocalTime,
                countView.text.toString().toInt(),
                Price(),
                "현장"
            )
            intent.putExtra(TicketResultActivity.INFO_KEY, ticketingInfo)
            it.context.startActivity(intent)
        }
    }

    fun initTimeSpinner(savedTimePosition: Int, dateSpinner: Spinner, timeSpinner: Spinner) {
        val times = movieDTO.playingTimes[dateSpinner.selectedItem] ?: emptyList()
        timeSpinner.adapter =
            ArrayAdapter(timeSpinner.context, android.R.layout.simple_spinner_item, times).apply {
                setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }
        timeSpinner.setSelection(savedTimePosition)
    }

    fun initDateSpinner(savedDatePosition: Int, dateSpinner: Spinner, timeSpinner: Spinner) {
        val dates = movieDTO.playingTimes.keys.sorted()
        dateSpinner.adapter =
            ArrayAdapter(dateSpinner.context, android.R.layout.simple_spinner_item, dates).apply {
                setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }
        dateSpinner.setSelection(savedDatePosition, false)
        dateSpinner.onItemSelectedListener = DateSpinnerListener(movieDTO.playingTimes, dates, timeSpinner)
    }

    fun initMinusButton(minusButton: Button, countView: TextView) {
        minusButton.setOnClickListener {
            val count = countView.text.toString().toInt()
            if (count > 1) countView.text = (count - 1).toString()
        }
    }

    fun initPlusButton(plusButton: Button, countView: TextView) {
        plusButton.setOnClickListener {
            val count = countView.text.toString().toInt()
            countView.text = (count + 1).toString()
        }
    }

    fun initCount(savedCount: Int, countView: TextView) {
        countView.text = savedCount.toString()
    }
}
