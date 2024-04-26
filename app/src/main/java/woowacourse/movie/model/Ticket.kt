package woowacourse.movie.model

import java.io.Serializable
import java.time.DayOfWeek
import java.time.LocalDate

class Ticket(
    ticketCount: Int = DEFAULT_TICKET_COUNT,
    screeningDate: String = DEFAULT_SCREENING_DATE,
    screeningTime: String = DEFAULT_SCREENING_TIME,
) : Serializable {
    var count: Int = ticketCount
        private set
    var screeningDate: String = screeningDate
        private set
    var screeningTime: String = screeningTime
        private set

    fun increaseCount(): ChangeTicketCountResult {
        if (count >= MAX_TICKET_COUNT) return OutOfRange
        count++
        return InRange
    }

    fun decreaseCount(): ChangeTicketCountResult {
        if (count <= MIN_TICKET_COUNT) return OutOfRange
        count--
        return InRange
    }

    fun calculatePrice(): Int = count * PRICE

    fun updateScreeningDate(screeningDate: String) {
        this.screeningDate = screeningDate
    }

    fun updateScreeningTime(screeningTime: String) {
        this.screeningTime = screeningTime
    }

    fun obtainScreeningTimes(date: LocalDate): List<Int> {
        return when (date.dayOfWeek) {
            DayOfWeek.SATURDAY, DayOfWeek.SUNDAY -> (9..23 step 2).map { it }
            else -> (10..24 step 2).map { it }
        }
    }

    fun obtainScreeningDates(
        firstScreeningDate: LocalDate,
        lastScreeningDate: LocalDate,
    ): List<LocalDate> {
        val dates = mutableListOf<LocalDate>()
        var currentDate = firstScreeningDate

        while (!currentDate.isAfter(lastScreeningDate)) {
            dates.add(currentDate)
            currentDate = currentDate.plusDays(1)
        }

        return dates.toList()
    }

    companion object {
        private const val PRICE = 13_000
        private const val DEFAULT_TICKET_COUNT = 1
        private const val DEFAULT_SCREENING_DATE = ""
        private const val DEFAULT_SCREENING_TIME = ""
        private const val MAX_TICKET_COUNT = 100
        private const val MIN_TICKET_COUNT = 1
    }
}
