package woowacourse.movie.view

import android.content.Intent
import android.widget.Button
import woowacourse.movie.activity.ReservationResultActivity
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.Price
import woowacourse.movie.domain.Reservation
import woowacourse.movie.domain.ReservationDetail
import woowacourse.movie.domain.discountPolicy.Discount
import woowacourse.movie.domain.discountPolicy.MovieDay
import woowacourse.movie.domain.discountPolicy.OffTime
import java.time.LocalDateTime

class ReservationButton(
    private val button: Button,
    private val extraName: String,
    movie: Movie,
    dateSpinner: DateSpinner,
    timeSpinner: TimeSpinner,
    counter: Counter
) {
    init {
        button.setOnClickListener {
            val reservationDetail = makeReservationDetail(dateSpinner, timeSpinner, counter)
            val reservation = makeReservation(movie, reservationDetail)
            startReservationResultActivity(reservation)
        }
    }

    private fun makeReservationDetail(
        dateSpinner: DateSpinner,
        timeSpinner: TimeSpinner,
        counter: Counter
    ): ReservationDetail {
        return ReservationDetail(
            LocalDateTime.of(
                (dateSpinner.spinner.spinner.selectedItem as LocalFormattedDate).date,
                (timeSpinner.spinner.spinner.selectedItem as LocalFormattedTime).time
            ),
            counter.count, Price()
        )
    }

    private fun makeReservation(movie: Movie, reservationDetail: ReservationDetail): Reservation {
        val discount = Discount(listOf(MovieDay, OffTime))
        val discountedReservationDetail = discount.calculate(reservationDetail)
        return Reservation(movie, discountedReservationDetail)
    }

    private fun startReservationResultActivity(reservation: Reservation) {
        val intent = Intent(button.context, ReservationResultActivity::class.java)
        intent.putExtra(extraName, reservation)
        button.context.startActivity(intent)
    }
}
