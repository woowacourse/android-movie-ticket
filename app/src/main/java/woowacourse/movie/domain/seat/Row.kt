package woowacourse.movie.domain.seat

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
@JvmInline
value class Row(
    val value: Int
) : Parcelable {
    init {
        require(value >= MINIMUM_ROW) { "행은 0보다 큰 숫자만 올 수 있습니다." }
    }

    companion object {
        private const val MINIMUM_ROW = 0
    }
}

