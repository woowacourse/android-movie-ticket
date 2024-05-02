package woowacourse.movie.model.time

import java.time.LocalDate

class ScreeningDateRange(
    private val start: ScreeningDate,
    private val end: ScreeningDate,
) {
    constructor(start: LocalDate, end: LocalDate) : this(ScreeningDate(start), ScreeningDate(end))

    fun toList(): List<ScreeningDate> {
        return generateSequence(start) { it.nextDay() }
            .takeWhile { !it.date.isAfter(end.date) }
            .toList()
    }
}

operator fun LocalDate.rangeTo(that: LocalDate): ScreeningDateRange {
    return ScreeningDateRange(this, that)
}
