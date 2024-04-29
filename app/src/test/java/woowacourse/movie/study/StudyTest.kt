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
        val actual = spreadDateWithRange(startDate, endDate)
        assertThat(actual).hasSize(1)
    }

    @Test
    fun test2() {
        val startDate = LocalDate.of(2024, 1, 1)
        val endDate = LocalDate.of(2024, 1, 2)
        val actual = spreadDateWithRange(startDate, endDate)
        assertThat(actual).hasSize(2)
    }

    @Test
    fun test3() {
        val startDate = LocalDate.of(2024, 11, 1)
        val endDate = LocalDate.of(2024, 12, 31)
        val actual = spreadDateWithRange(startDate, endDate)
        assertThat(actual).hasSize(61)
    }

    @Test
    fun test4() {
        val startDate = LocalDate.of(1999, 1, 1)
        val endDate = LocalDate.of(2199, 12, 31)
        val actual = spreadDateWithRange(startDate, endDate)
        assertThat(actual).hasSize(73414)
    }

    private fun spreadDateWithRange(
        startDate: LocalDate,
        endDate: LocalDate,
    ): List<LocalDate> {
        return generateSequence(startDate) { it.plusDays(1) }
            .takeWhile { it in startDate..endDate }
            .toList()
    }

    private fun spreadDateWithRange2(
        startDate: LocalDate,
        endDate: LocalDate,
    ): List<LocalDate> {
        val datesRange = startDate..endDate
        return generateSequence(startDate) { it.plusDays(1) }
            .takeWhile { it in datesRange }
            .toList()
        return emptyList()
    }

    private fun spreadDateWithDuration(
        startDate: LocalDate,
        endDate: LocalDate,
    ): List<LocalDate> {
        val diffDays = Duration.between(startDate.atStartOfDay(), endDate.atStartOfDay()).toDays()
        return listOf(startDate) + List(diffDays.toInt()) { startDate.plusDays(1) }
    }
}