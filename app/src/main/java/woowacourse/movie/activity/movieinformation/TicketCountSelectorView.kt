package woowacourse.movie.activity.movieinformation

import android.os.Bundle
import android.widget.Toast
import woowacourse.movie.R
import woowacourse.movie.databinding.ActivityReservationBinding
import woowacourse.movie.domain.reservation.TicketCount
import woowacourse.movie.uimodel.TicketCountModel

class TicketCountSelectorView(
    private val binding: ActivityReservationBinding
) {

    fun set(instance: Bundle?) {
        setTicketCountTextView(instance)
        binding.ticketCountMinusButton.setOnClickListener { ticketCountMinusButtonClickEvent() }
        binding.ticketCountPlusButton.setOnClickListener { ticketCountPlusButtonClickEvent() }
    }

    private fun setTicketCountTextView(instance: Bundle?) {
        binding.ticketCountTextView.text = TicketCount.MINIMUM.toString()
        if (instance != null) binding.ticketCountTextView.text =
            instance.getInt(TicketCountModel.TICKET_COUNT_INSTANCE_KEY).toString()
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
