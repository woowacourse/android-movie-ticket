package woowacourse.movie.view

import android.os.Bundle
import android.widget.TextView

open class SaveStateTextView(private val textView: TextView, override val saveStateKey: String) :
    SaveState {
    override fun save(outState: Bundle) {
        outState.putString(saveStateKey, textView.text.toString())
    }

    override fun load(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            val savedText = savedInstanceState.getString(saveStateKey)
            applyToView(savedText)
        }
    }

    fun applyToView(text: String?) {
        textView.text = text
    }
}
