package woowacourse.movie.domain

import java.time.LocalDateTime

data class Screening(val screeningDateTime: LocalDateTime) : Comparable<Screening> {
    override fun compareTo(other: Screening): Int =
        screeningDateTime.compareTo(other.screeningDateTime)
}
