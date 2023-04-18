package woowacourse.movie.view

import android.widget.Button
import android.widget.TextView

class Counter(
    minusButton: Button,
    plusButton: Button,
    private val countText: TextView,
    savedStateKey: String
) : SaveStateTextView(countText, saveStateKey = savedStateKey) {
    init {
        applyToView(INITIAL_COUNT.toString())
        minusButton.setOnClickListener {
            applyToView((getCount() - COUNT_FACTOR).toString())
        }

        plusButton.setOnClickListener {
            applyToView((getCount() + COUNT_FACTOR).toString())
        }
    }

    fun getCount(): Int {
        return countText.text.toString().toInt()
    }

    companion object {
        private const val INITIAL_COUNT = 1
        private const val COUNT_FACTOR = 1
    }
}
