package woowacourse.movie.reservation.model

import java.io.Serializable

@JvmInline
value class Count(val number: Int) : Serializable {
    init {
        require(number >= 1) { "개수는 1 이상이어야 합니다." }
    }

    operator fun inc(): Count {
        return Count(number + 1)
    }

    operator fun dec(): Count {
        return Count(number - 1)
    }
}
