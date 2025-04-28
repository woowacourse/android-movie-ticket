package woowacourse.movie.domain.model

import java.io.Serializable

@JvmInline
value class Row(val value: Int) : Serializable {
    init {
        require(value >= 0) { INVALID_VALUE_RANGE }
    }

    companion object {
        const val INVALID_VALUE_RANGE = "행의 숫자는 0과 같거나 커야 합니다"
    }
}
