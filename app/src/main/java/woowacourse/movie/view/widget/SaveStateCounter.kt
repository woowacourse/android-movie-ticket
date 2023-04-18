package woowacourse.movie.view.widget

import android.os.Bundle
import woowacourse.movie.domain.Count

class SaveStateCounter(val counter: Counter, override val saveStateKey: String) :
    SaveState {
    val count
        get() = counter.count

    override fun save(outState: Bundle) {
        outState.putInt(saveStateKey, counter.count.value)
    }

    override fun load(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            counter.count = Count(savedInstanceState.getInt(saveStateKey))
            applyToView()
        }
    }

    fun applyToView() {
        counter.applyToView()
    }
}
