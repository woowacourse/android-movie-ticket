package woowacourse.movie.view.model

import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.view.mapper.TicketsMapper
import java.io.Serializable
import java.text.NumberFormat
import java.time.format.DateTimeFormatter
import java.util.*

class TicketsUiModel(val list: List<TicketUiModel>) : UiModel, Serializable {
    fun renderSeatsInformation(seatTextView: TextView) {
        list.forEachIndexed { index, ticket ->
            ticket.seat.renderSeatInformation(seatTextView)
            addComma(index, seatTextView)
        }
    }

    fun renderPeopleCount(peopleCountTextView: TextView) {
        peopleCountTextView.text =
            peopleCountTextView.context.getString(R.string.reservation_people_count)
                .format(list.size)
    }

    fun renderDate(dateTextView: TextView) {
        val dateFormat =
            DateTimeFormatter.ofPattern(dateTextView.context.getString(R.string.reservation_datetime_format))
        dateTextView.text = dateFormat.format(list.first().date)
    }

    fun renderPrice(priceTextView: TextView) {
        val tickets = TicketsMapper.toDomain(this)
        val formattedPrice =
            NumberFormat.getNumberInstance(Locale.US).format(tickets.price.value)
        priceTextView.text =
            priceTextView.context.getString(R.string.reservation_price).format(formattedPrice)
    }

    private fun addComma(index: Int, textView: TextView) {
        if (index != list.lastIndex) textView.text =
            textView.text.toString() + textView.context.getString(
                R.string.comma
            ) + " "
    }
}
