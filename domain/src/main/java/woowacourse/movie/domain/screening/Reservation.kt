package woowacourse.movie.domain.screening

import woowacourse.movie.domain.discount.DiscountApplicator
import woowacourse.movie.domain.discount.Money
import woowacourse.movie.domain.theater.Point
import woowacourse.movie.domain.theater.Theater
import java.time.LocalDateTime
import kotlin.properties.Delegates

class Reservation(
    val movie: Movie,
    val screeningDateTime: LocalDateTime,
    val theater: Theater,
    val seatPoints: List<Point>
) {

    var id: Long? by Delegates.vetoable(null) { _, old, new ->
        old == null && new != null
    }

    val fee: Money = seatPoints.sumOf {
        DiscountApplicator.applyDiscount(screeningDateTime, theater.getFeeOf(it) ?: Money(0))
    }

    init {
        require(seatPoints.isNotEmpty()) { RESERVE_WITHOUT_SEAT_POINT_ERROR }
        require(seatPoints.all { theater.hasSeatOn(it) }) { RESERVE_WITH_NOT_EXIST_SEAT_ERROR }
    }

    private fun Collection<Point>.sumOf(selector: (Point) -> Money): Money {
        var sum = Money(0)
        for (element in this) {
            sum += selector(element)
        }
        return sum
    }

    companion object {
        private const val RESERVE_WITHOUT_SEAT_POINT_ERROR = "최소 한 개 이상의 좌석을 예매해야 합니다."
        private const val RESERVE_WITH_NOT_EXIST_SEAT_ERROR = "예매하려는 좌석이 존재하지 않습니다."
    }
}
