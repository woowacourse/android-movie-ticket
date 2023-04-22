package woowacourse.movie.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
@JvmInline
value class CountState private constructor(val value: Int) : Parcelable {

    operator fun minus(other: Int): CountState = of(value - other)
    operator fun plus(other: Int): CountState = of(value + other)

    companion object {

        fun of(value: Int): CountState {
            if (value < 1) return CountState(1)
            if (value > 20) return CountState(20)
            return CountState(value)
        }
    }
}
