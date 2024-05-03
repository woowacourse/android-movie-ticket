package woowacourse.movie.study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.Duration
import java.time.LocalDate

class StudyTest {
    @Test
    fun test1() {
        val startDate = LocalDate.of(2024, 1, 1)
        val endDate = LocalDate.of(2024, 1, 1)
        val actual = this.spreadDate4(startDate, endDate)
        assertThat(actual).hasSize(1)
    }

    @Test
    fun test2() {
        val startDate = LocalDate.of(2024, 1, 1)
        val endDate = LocalDate.of(2024, 1, 2)
        val actual = this.spreadDate4(startDate, endDate)
        assertThat(actual).hasSize(2)
    }

    @Test
    fun test3() {
        val startDate = LocalDate.of(2024, 11, 1)
        val endDate = LocalDate.of(2024, 12, 31)
        val actual = this.spreadDate4(startDate, endDate)
        assertThat(actual).hasSize(61)
    }

    @Test
    fun test4() {
        val startDate = LocalDate.of(1999, 1, 1)
        val endDate = LocalDate.of(2199, 12, 31)
        val actual = this.spreadDate4(startDate, endDate)
        assertThat(actual).hasSize(73414)
    }

    private fun spreadDate1(
        startDate: LocalDate,
        endDate: LocalDate,
    ): List<LocalDate> {
        return generateSequence(startDate) { it.plusDays(1) }
            .takeWhile { it in startDate..endDate }
            .toList()
    }

    private fun spreadDate2(
        startDate: LocalDate,
        endDate: LocalDate,
    ): List<LocalDate> {
        val datesRange = startDate..endDate
        return generateSequence(startDate) { it.plusDays(1) }
            .takeWhile { it in datesRange }
            .toList()
        return emptyList()
    }

    private fun spreadDate3(
        startDate: LocalDate,
        endDate: LocalDate,
    ): List<LocalDate> {
        val diffDays = Duration.between(startDate.atStartOfDay(), endDate.atStartOfDay()).toDays()
        return listOf(startDate) + List(diffDays.toInt()) { startDate.plusDays(1) }
    }

    private fun spreadDate4(
        startDate: LocalDate,
        endDate: LocalDate,
    ): List<LocalDate> {
        var currentDate = startDate
        val days = mutableListOf<LocalDate>()
        while (currentDate.isBefore(endDate.plusDays(1))) {
            days.add(startDate)
            currentDate = currentDate.plusDays(1)
        }
        return days
    }
}
