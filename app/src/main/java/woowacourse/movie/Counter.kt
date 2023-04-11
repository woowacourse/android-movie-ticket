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
        applyToView()

        minus.setOnClickListener {
            count -= 1
            applyToView()
        }

        plus.setOnClickListener {
            count += 1
            applyToView()
        }
    }

    fun applyToView() {
        text.text = count.toString()
    }
}
