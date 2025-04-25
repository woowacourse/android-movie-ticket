package woowacourse.movie.presentation.reservation.result

import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.model.reservation.ReservationCount
import woowacourse.movie.domain.model.reservation.ReservationInfo
import woowacourse.movie.presentation.model.toUiModel
import woowacourse.movie.presentation.view.reservation.result.ReservationResultContract
import woowacourse.movie.presentation.view.reservation.result.ReservationResultPresenter
import java.time.LocalDateTime

class ReservationResultPresenterTest {
    private lateinit var view: ReservationResultContract.View
    private lateinit var presenter: ReservationResultContract.Presenter
    private val reservationInfo =
        ReservationInfo(
            "해리포터",
            LocalDateTime.of(2025, 4, 1, 0, 0),
            ReservationCount(1),
        )

    @BeforeEach
    fun setUp() {
        view = mockk()
        presenter = ReservationResultPresenter(view)
    }

    @Test
    fun `예매 정보를 불러온다`() {
        every { view.setScreen(any()) } just Runs

        presenter.fetchDate { reservationInfo.toUiModel() }

        verify {
            view.setScreen(
                withArg {
                    assert(it.title == reservationInfo.title)
                    assert(it.reservationDateTime == reservationInfo.reservationDateTime)
                    assert(it.reservationCount == 1)
                },
            )
        }
    }

    @Test
    fun `예매 정보를 불러오지 못한 경우 다이얼로그를 보여준다`() {
        every { view.showInvalidReservationInfoDialog() } just Runs

        presenter.fetchDate { null }

        verify { view.showInvalidReservationInfoDialog() }
    }
}
