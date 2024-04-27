package woowacourse.movie.domain.model

import java.time.LocalDate

data class DateRange(
    override val start: LocalDate,
    override val endInclusive: LocalDate,
) : ClosedRange<LocalDate> {
    fun allDates(): List<LocalDate> = (start..endInclusive).toList()
}

private fun ClosedRange<LocalDate>.toList(): List<LocalDate> =
    generateSequence(start) {
        it.plusDays(1)
    }.takeWhile { it in start..endInclusive }.toList()
