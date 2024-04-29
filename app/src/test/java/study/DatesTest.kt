package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate

class DatesTest {
    @Test
    fun test1() {
        val startDate = LocalDate.of(2024, 1, 1)
        val endDate = LocalDate.of(2024, 1, 1)
        val actual: List<LocalDate> = dates(startDate, endDate)
        assertThat(actual).hasSize(1)
    }

    @Test
    fun test2() {
        val startDate = LocalDate.of(2024, 1, 1)
        val endDate = LocalDate.of(2024, 1, 2)
        val actual: List<LocalDate> = dates(startDate, endDate)
        assertThat(actual).hasSize(2)
    }

    @Test
    fun test3() {
        val startDate = LocalDate.of(2024, 11, 1)
        val endDate = LocalDate.of(2024, 12, 31)
        val actual: List<LocalDate> = dates(startDate, endDate)
        assertThat(actual).hasSize(61)
    }

    @Test
    fun test4() {
        val startDate = LocalDate.of(1999, 1, 1)
        val endDate = LocalDate.of(2199, 12, 31)
        val actual: List<LocalDate> = dates(startDate, endDate)
        assertThat(actual).hasSize(73414)
    }

    private fun dates(
        startDate: LocalDate,
        endDate: LocalDate,
    ): List<LocalDate> {
        val dates = startDate..endDate
        return generateSequence(startDate) { it.plusDays(1) }
            .takeWhile { it in dates }
            .toList()
    }

    private fun dates2(
        startDate: LocalDate,
        endDate: LocalDate,
    ): List<LocalDate> {
        val dates = mutableListOf(startDate)
        var span = 0L
        while (startDate.plusDays(span) < endDate) {
            dates.add(startDate.plusDays(span))
            span++
        }
        return dates
    }
}
