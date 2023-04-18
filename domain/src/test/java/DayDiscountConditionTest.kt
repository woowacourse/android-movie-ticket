package woowacourse.movie.domain

import com.example.domain.DayDiscountCondition
import com.example.domain.Minute
import com.example.domain.Movie
import com.example.domain.Reservation
import org.junit.Test
import java.time.LocalDate
import java.time.LocalDateTime

class DayDiscountConditionTest {

    @Test
    fun `만약 예매 날짜가 할인 날짜에 포함된다면 할인 조건을 만족한다`() {
        val dayDiscountCondition = DayDiscountCondition(listOf(1, 2, 3))
        val movie = Movie(
            "아바타",
            LocalDate.of(2023, 3, 1),
            LocalDate.of(2023, 3, 31),
            Minute(120),
            1,
            "줄거리"
        )

        val reservation = Reservation(movie, 2, LocalDateTime.of(2023, 3, 1, 0, 0))

        assert(dayDiscountCondition.isSatisfiedBy(reservation))
    }
}
