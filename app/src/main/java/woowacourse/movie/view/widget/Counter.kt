package woowacourse.movie.view.widget

import android.widget.Button
import android.widget.TextView
import woowacourse.movie.domain.Count

class Counter(
    minusButton: Button,
    plusButton: Button,
    private val text: TextView,
    var count: Count
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
        text.text = count.value.toString()
    }

    companion object {
        const val COUNT_FACTOR = 1
    }
}
