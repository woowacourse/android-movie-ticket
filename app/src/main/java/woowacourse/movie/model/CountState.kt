package woowacourse.movie.model

import android.os.Parcelable
import com.example.domain.model.Count
import kotlinx.parcelize.Parcelize

@Parcelize
@JvmInline
value class CountState private constructor(val value: Int) : Parcelable {

    operator fun minus(other: Int): CountState = CountState.of(value - other)
    operator fun plus(other: Int): CountState = CountState.of(value + other)

    companion object {
        fun from(count: Count): CountState {
            return CountState(count.value)
        }

        fun of(value: Int): CountState {
            if (value < 1) return CountState(1)
            return CountState(value)
        }
    }
}
