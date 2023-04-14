package woowacourse.movie.view

import android.widget.Button
import android.widget.TextView

class Counter(
    minusButton: Button,
    plusButton: Button,
    private val text: TextView,
    var count: Int
) {
    init {
        minusButton.setOnClickListener {
            count -= COUNT_FACTOR
            applyToView()
        }

        plusButton.setOnClickListener {
            count += COUNT_FACTOR
            applyToView()
        }
    }

    fun applyToView() {
        text.text = count.toString()
    }

    companion object {
        private const val COUNT_FACTOR = 1
    }
}
