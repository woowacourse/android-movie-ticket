package woowacourse.movie.domain.fixture

import woowacourse.movie.domain.model.booking.ScreeningDate
import java.time.LocalDate

val screeningDateFixture =
    ScreeningDate(
        startDate = LocalDate.of(2025, 5, 1),
        endDate = LocalDate.of(2025, 5, 4),
    )
