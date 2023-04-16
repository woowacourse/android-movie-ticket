package woowacourse.movie.model

import android.os.Parcelable
import com.example.domain.model.Count
import kotlinx.parcelize.Parcelize

@Parcelize
@JvmInline
value class CountState private constructor(val value: Int) : Parcelable {
    companion object {
        fun from(count: Count): CountState {
            return CountState(count.value)
        }
    }
}
