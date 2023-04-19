package woowacourse.movie.domain

import java.io.Serializable

@JvmInline
value class PeopleCount(val count: Int = MINIMUM_COUNT) : Serializable {
    fun minusCount(): PeopleCount {
        if (count == MINIMUM_COUNT) return this
        return PeopleCount(count - 1)
    }

    fun plusCount(): PeopleCount = PeopleCount(count + 1)

    companion object {
        const val MINIMUM_COUNT = 1
    }
}
