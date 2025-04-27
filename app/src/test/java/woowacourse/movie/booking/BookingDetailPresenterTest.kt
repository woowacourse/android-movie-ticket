package woowacourse.movie.booking

import android.os.Bundle
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.booking.detail.BookingDetailContract
import woowacourse.movie.booking.detail.BookingDetailPresenter
import woowacourse.movie.mapper.toUiModel
import woowacourse.movie.model.HeadCount
import woowacourse.movie.model.Movie
import woowacourse.movie.model.Seats
import woowacourse.movie.model.Ticket
import woowacourse.movie.movie.MovieUiModel
import woowacourse.movie.util.Formatter.formatDateDotSeparated
import woowacourse.movie.util.Formatter.formatTimeWithMidnight24
import java.time.LocalDate
import java.time.LocalTime

class BookingDetailPresenterTest {
    private lateinit var presenter: BookingDetailPresenter
    private lateinit var mockView: BookingDetailContract.View
    private lateinit var mockMovie: Movie
    private lateinit var mockMovieUiData: MovieUiModel
    private lateinit var mockTicket: Ticket

    @BeforeEach
    fun setUp() {
        mockView = mockk(relaxed = true)
        mockMovie =
            Movie(
                title = "해리 포터와 마법사의 돌",
                imageSource = "harry_potter.png",
                screeningStartDate = LocalDate.of(2028, 10, 1),
                screeningEndDate = LocalDate.of(2028, 10, 25),
                runningTime = 150,
            )

        mockTicket =
            Ticket(
                title = "해리 포터와 마법사의 돌",
                headCount = HeadCount(2),
                selectedDate = LocalDate.of(2028, 10, 13),
                selectedTime = LocalTime.of(11, 0),
                seats = Seats(emptyList()),
            )

        mockMovieUiData = mockMovie.toUiModel()
        presenter = BookingDetailPresenter(view = mockView, movie = mockMovieUiData)
    }

    @Test
    fun `영화가 null이면 오류 메시지를 띄우고 종료한다`() {
        presenter = BookingDetailPresenter(view = mockView, movie = null)

        presenter.initializeData(null)

        verify { mockView.showToastErrorAndFinish("영화 정보를 불러올 수 없습니다.") }
        confirmVerified(mockView)
    }

    @Test
    fun `영화가 주어지면 View에 초기 데이터를 보여준다`() {
        presenter = BookingDetailPresenter(view = mockView, movie = mockMovieUiData)

        presenter.initializeData(null)

        verify { mockView.showMovieInfo(mockMovieUiData) }
        verify { mockView.showTicket(any()) }

        verify { mockView.showScreeningDates(any(), any()) }
        verify { mockView.showScreeningTimes(any(), any()) }
    }

    @Test
    fun `영화가 주어졌을 때 날짜를 선택하면 Ticket에 해당 날짜가 반영되어 화면에 표시된다`() {
        presenter = BookingDetailPresenter(view = mockView, movie = mockMovieUiData)
        presenter.initializeData(null)

        val selectedDate = LocalDate.of(2028, 10, 13)
        presenter.onDateSelected(selectedDate)

        val formattedDate = formatDateDotSeparated(selectedDate)

        verify { mockView.showTicket(match { it.selectedDateText == formattedDate }) }
    }

    @Test
    fun `평일 날짜를 선택하면 해당하는 날짜에 맞는 시간대가 화면에 표시된다`() {
        presenter = BookingDetailPresenter(view = mockView, movie = mockMovieUiData)
        presenter.initializeData(null)

        // 평일임
        val selectedDate = LocalDate.of(2028, 10, 13)
        presenter.onDateSelected(selectedDate)

        val formattedDate = formatDateDotSeparated(selectedDate)

        verify { mockView.showTicket(match { it.selectedDateText == formattedDate }) }
        verify { mockView.showTicket(match { it.selectedTimeText == "09:00" }) }
    }

    @Test
    fun `주말 날짜를 선택하면 해당하는 날짜에 맞는 시간대가 화면에 표시된다`() {
        presenter = BookingDetailPresenter(view = mockView, movie = mockMovieUiData)
        presenter.initializeData(null)

        // 주말임
        val selectedDate = LocalDate.of(2028, 10, 14)
        presenter.onDateSelected(selectedDate)

        val formattedDate = formatDateDotSeparated(selectedDate)

        verify { mockView.showTicket(match { it.selectedDateText == formattedDate }) }
        verify { mockView.showTicket(match { it.selectedTimeText == "10:00" }) }
    }

    @Test
    fun `날짜와 시간을 선택하면 해당하는 내용이 화면에 표시된다`() {
        presenter = BookingDetailPresenter(view = mockView, movie = mockMovieUiData)
        presenter.initializeData(null)

        // 평일임
        val selectedDate = LocalDate.of(2028, 10, 13)
        val selectedTime = LocalTime.of(23, 0)
        presenter.onDateSelected(selectedDate)
        presenter.onTimeSelected(selectedTime)

        val formattedDate = formatDateDotSeparated(selectedDate)
        val formattedTime = formatTimeWithMidnight24(selectedTime)

        verify { mockView.showTicket(match { it.selectedDateText == formattedDate }) }
        verify { mockView.showTicket(match { it.selectedTimeText == formattedTime }) }
    }

    @Test
    fun `+버튼을 누르면 인원수가 0인 경우에 1명씩 추가됨을 화면에 표시한다`() {
        presenter = BookingDetailPresenter(view = mockView, movie = mockMovieUiData)
        presenter.initializeData(null)

        // 평일임
        val selectedDate = LocalDate.of(2028, 10, 13)
        val selectedTime = LocalTime.of(23, 0)
        presenter.onDateSelected(selectedDate)
        presenter.onTimeSelected(selectedTime)

        val formattedDate = formatDateDotSeparated(selectedDate)
        val formattedTime = formatTimeWithMidnight24(selectedTime)

        presenter.onHeadCountIncreased()

        verify { mockView.showTicket(match { it.selectedDateText == formattedDate }) }
        verify { mockView.showTicket(match { it.selectedTimeText == formattedTime }) }

        verify { mockView.showTicket(match { it.headCount == 1 }) }
    }

    @Test
    fun `-버튼을 누르면 인원수가 0인 경우에 버튼을 눌러도 인원수가 변경되지 않는다`() {
        presenter = BookingDetailPresenter(view = mockView, movie = mockMovieUiData)
        presenter.initializeData(null)

        // 평일임
        val selectedDate = LocalDate.of(2028, 10, 13)
        val selectedTime = LocalTime.of(23, 0)

        presenter.onDateSelected(selectedDate)
        presenter.onTimeSelected(selectedTime)

        val formattedDate = formatDateDotSeparated(selectedDate)
        val formattedTime = formatTimeWithMidnight24(selectedTime)
        presenter.onHeadCountDecreased()

        verify { mockView.showTicket(match { it.selectedDateText == formattedDate }) }
        verify { mockView.showTicket(match { it.selectedTimeText == formattedTime }) }

        verify { mockView.showTicket(match { it.headCount == 0 }) }
    }

    @Test
    fun `예매 확인버튼을 누르면 좌석 선택 화면으로 넘어간다`() {
        val selectedDate = LocalDate.of(2028, 10, 13)
        val selectedTime = LocalTime.of(23, 0)
        presenter.initializeData(null)

        presenter.onDateSelected(selectedDate)
        presenter.onTimeSelected(selectedTime)

        presenter.onHeadCountIncreased()
        presenter.onConfirmReservation()

        val formattedDate = formatDateDotSeparated(selectedDate)
        val formattedTime = formatTimeWithMidnight24(selectedTime)

        verify {
            mockView.startSeatSelectionActivity(
                match {
                    it.selectedDateText == formattedDate &&
                        it.selectedTimeText == formattedTime &&
                        it.headCount == 1
                },
            )
        }
    }

    @Test
    fun `인원수가 10명인 경우 -버튼을 눌렀을때 인원수가 줄어드는 것을 화면에서 볼 수 있다`() {
        val mockBundle =
            mockk<Bundle> {
                every { getString("SCREENING_DATE") } returns "2028-10-13"
                every { getString("SCREENING_TIME") } returns "11:00"
                every { getInt("HEAD_COUNT") } returns 10
            }

        presenter = BookingDetailPresenter(view = mockView, movie = mockMovieUiData)
        presenter.initializeData(mockBundle)

        presenter.onHeadCountDecreased()

        verify { mockView.showTicket(match { it.headCount == 9 }) }
    }

    @Test
    fun `상태 저장 시 예상한 값들이 Bundle에 저장된다`() {
        val presenter = BookingDetailPresenter(view = mockView, movie = mockMovieUiData)

        presenter.initializeData(null)
        presenter.onDateSelected(LocalDate.of(2028, 10, 13))
        presenter.onTimeSelected(LocalTime.of(11, 0))
        presenter.onHeadCountIncreased()

        val mockBundle = mockk<Bundle>(relaxed = true)
        presenter.onSaveState(mockBundle)

        verify { mockBundle.putString("SCREENING_DATE", "2028-10-13") }
        verify { mockBundle.putString("SCREENING_TIME", "11:00") }
        verify { mockBundle.putInt("HEAD_COUNT", 1) }
    }

    @Test
    fun `상태를 복원했을 때 화면에 이전 값이 그대로 표시된다`() {
        val savedBundle =
            mockk<Bundle> {
                every { getString("SCREENING_DATE") } returns "2028-10-13"
                every { getString("SCREENING_TIME") } returns "11:00"
                every { getInt("HEAD_COUNT") } returns 3
            }

        presenter = BookingDetailPresenter(view = mockView, movie = mockMovieUiData)

        presenter.initializeData(savedBundle)

        verify {
            mockView.showTicket(
                match {
                    it.selectedDateText == "2028.10.13" &&
                        it.selectedTimeText == "11:00" &&
                        it.headCount == 3
                },
            )
        }
    }

    @Test
    fun `저장된 인원 수가 있으면 복원된다`() {
        val mockBundle =
            mockk<Bundle> {
                every { getString("SCREENING_DATE") } returns "2028-10-13"
                every { getString("SCREENING_TIME") } returns "11:00"
                every { getInt("HEAD_COUNT") } returns 10
            }

        presenter.initializeData(mockBundle)

        verify {
            mockView.showTicket(match { it.selectedTimeText == "11:00" })
            mockView.showTicket(match { it.selectedDateText == "2028.10.13" })
            mockView.showTicket(match { it.headCount == 10 })
        }
    }
}
