package woowacourse.movie

import android.widget.Button
import android.widget.TextView

class Counter(
    minus: Button,
    plus: Button,
    private val text: TextView,
    var count: Int
) {
    init {
        minus.setOnClickListener {
            count -= COUNT_FACTOR
            applyToView()
        }

        plus.setOnClickListener {
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
