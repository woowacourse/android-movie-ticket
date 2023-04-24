package woowacourse.movie.activity.movieinformation

import android.widget.Toast
import woowacourse.movie.R
import woowacourse.movie.databinding.ActivityReservationBinding
import woowacourse.movie.domain.reservation.TicketCount

class TicketCountSelectorView(
    private val binding: ActivityReservationBinding
) {

    fun set() {
        binding.ticketCountTextView.text = TicketCount.MINIMUM.toString()
        binding.ticketCountMinusButton.setOnClickListener { ticketCountMinusButtonClickEvent() }
        binding.ticketCountPlusButton.setOnClickListener { ticketCountPlusButtonClickEvent() }
    }

    private fun ticketCountMinusButtonClickEvent() {
        runCatching {
            val ticketCount =
                TicketCount(binding.ticketCountTextView.text.toString().toInt() - 1)
            binding.ticketCountTextView.text = ticketCount.value.toString()
        }.onFailure {
            val ticketCountConditionMessage =
                binding.root.context
                    .getString(R.string.ticket_count_condition_message_form)
                    .format(TicketCount.MINIMUM)
            Toast.makeText(
                binding.root.context,
                ticketCountConditionMessage,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun ticketCountPlusButtonClickEvent() {
        val ticketCount =
            TicketCount(binding.ticketCountTextView.text.toString().toInt() + 1)
        binding.ticketCountTextView.text = ticketCount.value.toString()
    }
}
