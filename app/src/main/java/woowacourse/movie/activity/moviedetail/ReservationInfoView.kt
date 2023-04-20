package woowacourse.movie.activity.moviedetail

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.activity.seatselect.SeatSelectActivity
import woowacourse.movie.activity.ticketresult.TicketResultActivity
import woowacourse.movie.domain.policy.DiscountPolicies
import woowacourse.movie.domain.ticket.Price
import woowacourse.movie.domain.ticket.Ticket
import woowacourse.movie.model.MovieModel
import woowacourse.movie.model.toPresentation
import woowacourse.movie.util.getKeyFromIndex
import woowacourse.movie.util.getOrEmptyList
import java.time.LocalDate
import java.time.LocalTime

class ReservationInfoView(private val viewGroup: ViewGroup) {
    private val reserveButton = viewGroup.findViewById<Button>(R.id.btn_reserve)
    private val timeSpinner = viewGroup.findViewById<Spinner>(R.id.spinner_time)
    private val dateSpinner = viewGroup.findViewById<Spinner>(R.id.spinner_date)
    private val minusButton = viewGroup.findViewById<Button>(R.id.btn_minus)
    private val countView = viewGroup.findViewById<TextView>(R.id.text_count)
    private val plusButton = viewGroup.findViewById<Button>(R.id.btn_plus)

    fun set(savedInstanceState: Bundle?, movie: MovieModel) {
        val savedCount = savedInstanceState?.getInt(MovieDetailActivity.COUNT_KEY) ?: DEFAULT_COUNT
        val savedDate =
            savedInstanceState?.getInt(MovieDetailActivity.SPINNER_DATE_KEY) ?: DEFAULT_POSITION
        val savedTime =
            savedInstanceState?.getInt(MovieDetailActivity.SPINNER_TIME_KEY) ?: DEFAULT_POSITION

        setCount(savedCount)
        setMinusButton()
        setPlusButton()
        setReserveButton(movie.title)
        setDateSpinner(savedDate, movie.playingDateTimes)
        setTimeSpinner(
            savedTime,
            movie.playingDateTimes.getOrEmptyList(movie.playingDateTimes.getKeyFromIndex(savedDate))
        )
    }

    private fun setReserveButton(title: String) {
        reserveButton.setOnClickListener {
            val intent = Intent(it.context, SeatSelectActivity::class.java)
            val ticket = Ticket.of(
                DiscountPolicies.policies,
                title,
                viewGroup.findViewById<Spinner>(R.id.spinner_date).selectedItem as LocalDate,
                viewGroup.findViewById<Spinner>(R.id.spinner_time).selectedItem as LocalTime,
                viewGroup.findViewById<TextView>(R.id.text_count).text.toString().toInt(),
                Price()
            )
            intent.putExtra(TicketResultActivity.INFO_KEY, ticket.toPresentation())
            it.context.startActivity(intent)
        }
    }

    private fun setTimeSpinner(savedTimePosition: Int, times: List<LocalTime>) {
        timeSpinner.adapter = SpinnerAdapter(times)
        timeSpinner.setSelection(savedTimePosition)
    }

    private fun setDateSpinner(
        savedDatePosition: Int,
        playingTimes: Map<LocalDate, List<LocalTime>>
    ) {
        dateSpinner.adapter = SpinnerAdapter(playingTimes.keys.toList())
        dateSpinner.setSelection(savedDatePosition, false)
        dateSpinner.setOnItemClickListener { parent, view, position, id ->
            val times = playingTimes.getOrEmptyList(playingTimes.getKeyFromIndex(position))
            timeSpinner.adapter = ArrayAdapter(viewGroup.context, android.R.layout.simple_spinner_item, times)
        }
    }

    private fun setMinusButton() {
        minusButton.setOnClickListener {
            val count = countView.text.toString().toInt()
            if (count > 1) countView.text = (count - 1).toString()
        }
    }

    private fun setPlusButton() {
        plusButton.setOnClickListener {
            val count = countView.text.toString().toInt()
            countView.text = (count + 1).toString()
        }
    }

    private fun setCount(savedCount: Int) {
        countView.text = savedCount.toString()
    }

    private fun <T> SpinnerAdapter(items: List<T>) =
        ArrayAdapter(viewGroup.context, android.R.layout.simple_spinner_item, items).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }

    companion object {
        private const val DEFAULT_COUNT = 1
        private const val DEFAULT_POSITION = 0
    }
}
