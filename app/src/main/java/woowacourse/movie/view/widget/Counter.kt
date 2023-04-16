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
            count = count.subtract()
            applyToView()
        }

        plusButton.setOnClickListener {
            count = count.add()
            applyToView()
        }
    }

    fun applyToView() {
        text.text = count.value.toString()
    }
}
