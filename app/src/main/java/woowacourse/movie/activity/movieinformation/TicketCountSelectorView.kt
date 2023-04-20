package woowacourse.movie.activity.movieinformation

import android.widget.Toast
import woowacourse.movie.R
import woowacourse.movie.databinding.ActivityReservationBinding

class TicketCountSelectorView(
    private val binding: ActivityReservationBinding
) {

    fun set() {
        binding.ticketCountTextView.text = reservation.TicketCount.MINIMUM.toString()
        binding.ticketCountMinusButton.setOnClickListener { ticketCountMinusButtonClickEvent() }
        binding.ticketCountPlusButton.setOnClickListener { ticketCountPlusButtonClickEvent() }
    }

    private fun ticketCountMinusButtonClickEvent() {
        runCatching {
            val ticketCount =
                reservation.TicketCount(binding.ticketCountTextView.text.toString().toInt() - 1)
            binding.ticketCountTextView.text = ticketCount.value.toString()
        }.onFailure {
            val ticketCountConditionMessage =
                binding.root.context
                    .getString(R.string.ticket_count_condition_message_form)
                    .format(reservation.TicketCount.MINIMUM)
            Toast.makeText(
                binding.root.context,
                ticketCountConditionMessage,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun ticketCountPlusButtonClickEvent() {
        val ticketCount =
            reservation.TicketCount(binding.ticketCountTextView.text.toString().toInt() + 1)
        binding.ticketCountTextView.text = ticketCount.value.toString()
    }
}
