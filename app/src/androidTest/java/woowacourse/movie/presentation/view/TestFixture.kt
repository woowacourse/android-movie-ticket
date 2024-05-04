package woowacourse.movie.presentation.view

import android.content.Context
import android.content.Intent
import woowacourse.movie.presentation.uimodel.MovieTicketUiModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

const val DEFAULT_MOVIE_ID = -1
const val DEFAULT_TITLE = "영화 제목"
const val DEFAULT_RUNNING_TIME = 0
const val DEFAULT_COUNT = 1
const val DEFAULT_SUMMARY = "영화 줄거리"
const val RESERVATION_COUNT = 2
const val TOTAL_PRICE = 27000
val DEFAULT_SCREENING_DATE = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE)
val DEFAULT_SCREENING_END_DATE = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE)

val selectedSeatList = listOf("C3", "B4")
val testMovieTicketUiModel =
    MovieTicketUiModel(
        ticketId = 0L,
        title = DEFAULT_TITLE,
        screeningDate = DEFAULT_SCREENING_DATE,
        startTime = DEFAULT_SCREENING_DATE,
        endTime = DEFAULT_SCREENING_END_DATE,
        runningTime = DEFAULT_RUNNING_TIME,
        reservationCount = RESERVATION_COUNT,
        totalPrice = TOTAL_PRICE,
        selectedSeats = selectedSeatList,
    )

fun detailActivityIntent(context: Context): Intent =
    Intent(context, MovieDetailActivity::class.java).apply {
        putExtra(MovieDetailActivity.INTENT_MOVIE_ID, DEFAULT_MOVIE_ID)
    }

fun seatSelectionActivityIntent(context: Context): Intent =
    Intent(context, SeatSelectionActivity::class.java).apply {
        putExtra(SeatSelectionActivity.INTENT_TITLE, DEFAULT_TITLE)
        putExtra(SeatSelectionActivity.INTENT_RESERVATION_COUNT, DEFAULT_COUNT)
    }

fun reservationResultActivityIntent(context: Context): Intent =
    Intent(context, ReservationResultActivity::class.java).apply {
        putExtra(ReservationResultActivity.INTENT_TICKET, testMovieTicketUiModel)
    }
