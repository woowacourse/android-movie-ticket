package woowacourse.movie.activity

import woowacourse.movie.domain.MemberCount
import woowacourse.movie.domain.PriceRule
import woowacourse.movie.dto.MovieDto
import java.time.LocalDate

class ReservationPresenter : ReservationContract.Presenter {
    override fun betweenDates(
        today: LocalDate,
        movieDto: MovieDto,
    ): List<LocalDate> {
        return movieDto.toMovie().betweenDates(today)
    }

    override fun price(memberCount: Int): Int {
        return MemberCount(memberCount) * PriceRule.NORMAL.price
    }
}
