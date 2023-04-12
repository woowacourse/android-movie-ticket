package woowacourse.movie.domain

import java.io.Serializable

data class Time(
    val hour: Int,
    val minute: Int = 0
) : Serializable {
    override fun toString(): String {
        return "%02d:%02d".format(hour, minute)
    }
}
