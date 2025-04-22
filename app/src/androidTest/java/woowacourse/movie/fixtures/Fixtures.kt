package woowacourse.movie.fixtures

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.Ticket
import woowacourse.movie.view.utils.toDomain
import woowacourse.movie.view.utils.toUiModel
import java.time.LocalDate
import java.time.LocalDateTime

val fakeContext: Context = ApplicationProvider.getApplicationContext()

val movie =
    Movie(
        title = "해리 포터와 마법사의 돌",
        startDate = LocalDate.of(2025, 4, 1),
        endDate = LocalDate.of(2025, 4, 25),
        runningTime = 152,
    ).toUiModel()

val ticket =
    Ticket(
        movie = movie.toDomain(),
        showtime = LocalDateTime.of(2025, 4, 15, 11, 0, 0),
        count = 2,
    ).toUiModel()
