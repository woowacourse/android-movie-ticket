package woowacourse.movie.ui.reservation

import android.view.View
import android.widget.Button
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.model.CountState
import woowacourse.movie.ui.Toaster

class ReservationCounter(
    view: View,
    initCountState: CountState? = null
) {
    private val minus: Button = view.findViewById(R.id.minus)
    private val plus: Button = view.findViewById(R.id.plus)
    private val countTextView: TextView = view.findViewById(R.id.count)

    var count: CountState = CountState.of(1)
        private set(value) {
            field = value
            countTextView.text = field.value.toString()
        }

    init {
        initCountState?.let { count = it }
        initSetOnClickListener()
    }

    private fun initSetOnClickListener() {
        minus.setOnClickListener {
            if (count.value == 1) {
                Toaster.showToast(
                    it.context,
                    it.context.getString(R.string.error_reservation_min_count)
                )
                return@setOnClickListener
            }
            count -= 1
        }

        plus.setOnClickListener { count += 1 }
    }
}
