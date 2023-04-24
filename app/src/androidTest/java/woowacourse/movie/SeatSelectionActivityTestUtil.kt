package woowacourse.movie

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import domain.seat.ScreeningSeat
import woowacourse.movie.model.SeatSelectionInfo
import woowacourse.movie.reservation.ReservationActivity
import woowacourse.movie.seatselection.ScreeningSeatSelectionActivity
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

val intent = Intent(
    ApplicationProvider.getApplicationContext(),
    ScreeningSeatSelectionActivity::class.java
)

lateinit var scenario: ActivityScenario<ScreeningSeatSelectionActivity>

fun startTest(seatSelectionInfo: SeatSelectionInfo?) {
    seatSelectionInfo?.let {
        intent.putExtra(ReservationActivity.SEAT_SELECTION_KEY, it)
    }
    scenario = ActivityScenario.launch(intent)
}

fun SeatSelectionInfo(seatCount: Int): SeatSelectionInfo = SeatSelectionInfo(
    movieName = "harrypoter",
    screeningTime = LocalDateTime.of(
        LocalDate.MIN,
        LocalTime.MIN
    ),
    seatCount = seatCount
)

fun ScreeningSeat.toText(): String = "${'A' + this.row.ordinal}${this.column.ordinal + 1}"
