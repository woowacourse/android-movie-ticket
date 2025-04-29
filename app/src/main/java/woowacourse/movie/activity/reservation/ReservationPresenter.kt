package woowacourse.movie.activity.reservation

import woowacourse.movie.domain.MemberCount
import woowacourse.movie.dto.MovieListDataDto.MovieDto
import woowacourse.movie.global.ServiceLocator
import woowacourse.movie.global.ServiceLocator.runningTimeRule
import java.time.LocalDate

class ReservationPresenter(private val reservationView: ReservationContract.View) : ReservationContract.Presenter {
    private var memberCount = MemberCount()

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

    override fun initRunningTimes(reservationDay: LocalDate) {
        val runningTimes = runningTimeRule.whenTargetDay(reservationDay, ServiceLocator.now)
        reservationView.initRunningTimes(runningTimes)
    }

    override fun initRunningDates(movieDto: MovieDto) {
        val runningDates = movieDto.toMovie().betweenDates(ServiceLocator.today)
        reservationView.initRunningDates(runningDates)
    }

    override fun changeRunningTimes(reservationDay: LocalDate) {
        reservationView.changeRunningTimes(
            runningTimeRule.whenTargetDay(
                reservationDay,
                ServiceLocator.now,
            ),
        )
    }
}
