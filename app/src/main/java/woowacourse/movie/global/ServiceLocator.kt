package woowacourse.movie.global

import woowacourse.movie.activity.MainContract
import woowacourse.movie.activity.MainPresenter
import woowacourse.movie.activity.ReservationContract
import woowacourse.movie.activity.ReservationPresenter
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.RunningTimeRuleImpl
import java.time.LocalDate
import java.time.LocalDateTime
import kotlin.time.Duration.Companion.minutes

object ServiceLocator {
    val movies: List<Movie> =
        listOf<Movie>(
            Movie(
                "해리포터와 마법사의 돌",
                "images/movie_poster.jpg",
                LocalDateTime.of(2025, 4, 3, 0, 0, 0),
                LocalDateTime.of(2025, 4, 5, 23, 59, 59),
                125.minutes,
            ),
        )
    val today: LocalDate = LocalDate.of(2025, 4, 3)
    val now: LocalDateTime = LocalDateTime.of(2025, 4, 3, 14, 0, 0)
    val runningTimeRule = RunningTimeRuleImpl()

    fun mainPresenter(view: MainContract.View): MainContract.Presenter = MainPresenter(view)

    fun reservationPresenter(view: ReservationContract.View): ReservationContract.Presenter = ReservationPresenter(view)
}
