package woowacourse.movie.activity

import woowacourse.movie.domain.MemberCount
import woowacourse.movie.domain.PriceRule
import woowacourse.movie.dto.MovieDto
import java.time.LocalDate

class ReservationPresenter(private val reservationView: ReservationContract.View) : ReservationContract.Presenter {
    private var memberCount = MemberCount()

    override fun betweenDates(
        today: LocalDate,
        movieDto: MovieDto,
    ): List<LocalDate> {
        return movieDto.toMovie().betweenDates(today)
    }

    override fun price(memberCount: Int): Int {
        return MemberCount(memberCount) * PriceRule.NORMAL.price
    }

    override fun addMember() {
        memberCount.increase().onSuccess {
            memberCount = it
            reservationView.updateMemberCount(Result.success(it.value))
        }
    }

    override fun removeMember() {
        memberCount.decrease().onSuccess {
            memberCount = it
            reservationView.updateMemberCount(Result.success(it.value))
        }.onFailure {
            reservationView.updateMemberCount(Result.failure(it))
        }
    }
}
