package woowacourse.movie.domain

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class TicketManager(
    private val movie: Movie,
) {
    private val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN)
    private var ticketCountValue = 0
    private var selectedDatePosition = 0
    private var selectedTimePosition = 0

    fun incrementTicketCount() {
        ticketCountValue++
    }

    fun decrementTicketCount() {
        ticketCountValue = (ticketCountValue - 1).coerceAtLeast(0)
    }

    fun getTicketCount(): Int = ticketCountValue

    fun setTicketCount(ticketCount: Int) {
        ticketCountValue = ticketCount
    }

    fun setDatePosition(position: Int) {
        selectedDatePosition = position
    }

    fun setTimePosition(position: Int) {
        selectedTimePosition = position
    }

    fun getDatePosition(): Int = selectedDatePosition

    fun getTimePosition(): Int = selectedTimePosition

    fun getDates(): List<String> {
        val dates = mutableListOf<String>()
        var current = movie.startDate

        while (!current.isAfter(movie.endDate)) {
            dates.add(current.format(dateFormatter))
            current = current.plusDays(1)
        }

        return dates
    }

    fun getTimes(date: String): List<String> {
        val parsedDate = LocalDate.parse(date, dateFormatter)

        val startHour =
            when (parsedDate.dayOfWeek) {
                DayOfWeek.SATURDAY, DayOfWeek.SUNDAY -> WEEKEND_START_HOUR
                else -> WEEKDAY_START_HOUR
            }

        return (startHour..LAST_HOUR step HOUR_STEP).map { hour ->
            String.format("%02d:00", hour)
        }
    }

    fun createTicket(): Ticket {
        val dateString = getDates()[selectedDatePosition]
        val timeString = getTimes(dateString)[selectedTimePosition]

        val date = LocalDate.parse(dateString, dateFormatter)
        val hour = timeString.substringBefore(":").toInt()
        val time = LocalTime.of(hour, 0)

        return Ticket(
            title = movie.title,
            date = date,
            time = time,
            count = ticketCountValue,
        )
    }

    companion object {
        private const val DATE_PATTERN = "yyyy.M.d"

        private const val WEEKEND_START_HOUR = 9
        private const val WEEKDAY_START_HOUR = 10
        private const val LAST_HOUR = 24
        private const val HOUR_STEP = 2
    }
}
