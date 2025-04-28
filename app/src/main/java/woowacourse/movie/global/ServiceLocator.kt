package woowacourse.movie.global

import woowacourse.movie.activity.main.MainContract
import woowacourse.movie.activity.main.MainPresenter
import woowacourse.movie.activity.reservation.ReservationContract
import woowacourse.movie.activity.reservation.ReservationPresenter
import woowacourse.movie.activity.reservation.ReservationSeatContract
import woowacourse.movie.activity.reservation.ReservationSeatPresenter
import woowacourse.movie.domain.MovieListData
import woowacourse.movie.domain.MovieListData.Movie
import woowacourse.movie.domain.rules.RunningTimeRuleImpl
import woowacourse.movie.domain.rules.SeatsFactoryImpl
import java.time.LocalDate
import java.time.LocalDateTime
import kotlin.time.Duration.Companion.minutes

object ServiceLocator {
    val movies: List<Movie> =
        (0..100).map {
            Movie(
                "해리포터와 마법사의 돌$it",
                "images/movie_poster.jpg",
                LocalDateTime.of(2025, 4, 3, 0, 0, 0),
                LocalDateTime.of(2025, 4, 5, 23, 59, 59),
                125.minutes,
            )
        }
    val ads: List<MovieListData.Ads> =
        listOf(
            MovieListData.Ads("ads/광고.png"),
        )
    val today: LocalDate = LocalDate.of(2025, 4, 3)
    val now: LocalDateTime = LocalDateTime.of(2025, 4, 3, 14, 0, 0)
    val runningTimeRule = RunningTimeRuleImpl()

    val seats = SeatsFactoryImpl().get()

    fun mainPresenter(view: MainContract.View): MainContract.Presenter = MainPresenter(view)

    fun reservationPresenter(view: ReservationContract.View): ReservationContract.Presenter = ReservationPresenter(view)

    fun reservationSeatPresenter(view: ReservationSeatContract.View): ReservationSeatContract.Presenter = ReservationSeatPresenter(view)
}
