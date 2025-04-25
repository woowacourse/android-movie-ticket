package woowacourse.movie.presenter

import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.activity.ReservationContract
import woowacourse.movie.dto.MovieDto
import woowacourse.movie.fixture.DomainFixture
import woowacourse.movie.global.ServiceLocator
import java.time.LocalDate
import java.time.LocalDateTime

class ReservationPresenterTest {
    private val view = mockk<ReservationContract.View>()
    private lateinit var presenter: ReservationContract.Presenter

    @BeforeEach
    fun setUp() {
        presenter = ServiceLocator.reservationPresenter(view)
    }

    @Test
    fun `인원을 증가시키는 요청이 들어오면 증가한 인원을 반환한다`() {
        every { view.updateMemberCount(Result.success(2)) } just runs
        presenter.addMember()
        verify { view.updateMemberCount(Result.success(2)) }
    }

    @Test
    fun `인원을 감소시키는 요청이 들어오면 증가한 인원을 반환한다`() {
        // given
        every { view.updateMemberCount(Result.success(1)) } just runs
        every { view.updateMemberCount(Result.success(2)) } just runs
        presenter.addMember()

        // when
        presenter.removeMember()

        // then
        verify { view.updateMemberCount(Result.success(1)) }
    }

    @Test
    fun `인원을 감소할수 없으면 요청에 실패한다`() {
        every {
            view.updateMemberCount(
                Result.failure(
                    IllegalArgumentException("영화 예매 수는 1명이상이어야합니다."),
                ),
            )
        } just runs

        presenter.removeMember()
        verify {
            view.updateMemberCount(
                Result.failure(
                    IllegalArgumentException("영화 예매 수는 1명이상이어야합니다."),
                ),
            )
        }
    }

    @Test
    fun `상영일을 초기화 할 수 있다`() {
        every { view.initRunningDates(any()) } just runs

        presenter.initRunningDates(
            LocalDate.of(2025, 4, 3),
            ServiceLocator.movies.map { MovieDto.fromMovie(it) }[0],
        )
        verify {
            view.initRunningDates(
                listOf(
                    LocalDate.of(2025, 4, 3),
                    LocalDate.of(2025, 4, 4),
                    LocalDate.of(2025, 4, 5),
                ),
            )
        }
    }

    @Test
    fun `상영 시간을 초기화 할 수 있다`() {
        every { view.initRunningTimes(any()) } just runs

        presenter.initRunningTimes(
            LocalDateTime.of(2025, 4, 3, 9, 0, 0),
            LocalDate.of(2025, 4, 5),
        )
        verify { view.initRunningTimes(DomainFixture.weekEndRule) }
    }

    @Test
    fun `상영 시간을 변경할 수 있다`() {
        every { view.changeRunningTimes(any()) } just runs

        presenter.changeRunningTimes(
            LocalDateTime.of(2025, 4, 3, 9, 0, 0),
            LocalDate.of(2025, 4, 5),
        )
        verify { view.changeRunningTimes(DomainFixture.weekEndRule) }
    }
}
