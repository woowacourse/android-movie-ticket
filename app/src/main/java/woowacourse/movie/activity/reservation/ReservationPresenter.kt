package woowacourse.movie.activity.reservation

import woowacourse.movie.domain.MemberCount
import woowacourse.movie.dto.MovieListData.MovieDto
import woowacourse.movie.global.ServiceLocator.runningTimeRule
import java.time.LocalDate
import java.time.LocalDateTime

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

    override fun initRunningTimes(
        now: LocalDateTime,
        reservationDay: LocalDate,
    ) {
        val runningTimes = runningTimeRule.whenTargetDay(reservationDay, now)
        reservationView.initRunningTimes(runningTimes)
    }

    override fun initRunningDates(
        today: LocalDate,
        movieDto: MovieDto,
    ) {
        val runningDates = movieDto.toMovie().betweenDates(today)
        reservationView.initRunningDates(runningDates)
    }

    override fun changeRunningTimes(
        now: LocalDateTime,
        reservationDay: LocalDate,
    ) {
        reservationView.changeRunningTimes(runningTimeRule.whenTargetDay(reservationDay, now))
    }
}
