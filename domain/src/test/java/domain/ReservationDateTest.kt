package woowacourse.movie.domain

import domain.screeningschedule.ReservationDate
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDate

class ReservationDateTest {
    @Test
    fun `시작 날짜와 끝나는 날짜 사이의 날짜들의 개수를 확인한다`() {
        val dates = ReservationDate(LocalDate.of(2023, 3, 1), LocalDate.of(2023, 3, 5))
        assertEquals(dates.getIntervalDays().size, 5)
    }
}
