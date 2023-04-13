package woowacourse.movie

import android.os.Bundle

class SaveStateCounter(val counter: Counter, override val saveStateKey: String) :
    SaveState {
    val count
        get() = counter.count

    override fun save(outState: Bundle) {
        outState.putInt(saveStateKey, counter.count)
    }

    override fun load(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            counter.count = savedInstanceState.getInt(saveStateKey)
        }
    }

    fun applyToView() {
        counter.applyToView()
    }
}
