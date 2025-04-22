package woowacourse.movie.model

class HeadCount(private var _value: Int = INITIAL_VALUE) {
    val value: Int
        get() = _value

    init {
        if(_value < INITIAL_VALUE) _value = INITIAL_VALUE
    }

    fun increase() {
        _value++
    }

    fun decrease() {
        if (_value == INITIAL_VALUE) return
        _value--
    }

    companion object {
        private const val INITIAL_VALUE = 1
    }
}