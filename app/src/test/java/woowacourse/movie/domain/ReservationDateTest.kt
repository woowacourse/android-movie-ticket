package woowacourse.movie.domain

import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.time.LocalDate

class ReservationDateTest() {
    @Test
    fun `시작 날짜와 끝나는 날짜 사이의 날짜들의 개수를 확인한다`() {
        val dates = ReservationDate(RunningDate(LocalDate.of(2023, 3, 1), LocalDate.of(2023, 3, 5)))
        assertEquals(dates.getScreeningDays().size, 5)
    }
}
