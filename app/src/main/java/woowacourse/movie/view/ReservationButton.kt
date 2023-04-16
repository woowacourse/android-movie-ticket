package woowacourse.movie.view

import android.content.Context
import android.content.Intent
import android.widget.Button
import woowacourse.movie.activity.ReservationResultActivity
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.MovieReservationOffice
import woowacourse.movie.domain.Reservation

class ReservationButton(
    private val button: Button,
    private val extraName: String,
    movie: Movie,
    dateSpinner: DateSpinner,
    timeSpinner: TimeSpinner,
    counter: Counter
) {
    private val movieReservationOffice = MovieReservationOffice()

    init {
        button.setOnClickListener {
            reservationButtonClick(dateSpinner, timeSpinner, counter, movie)
        }
    }

    private fun reservationButtonClick(
        dateSpinner: DateSpinner,
        timeSpinner: TimeSpinner,
        counter: Counter,
        movie: Movie,
    ) {
        val reservationDetail = movieReservationOffice.makeReservationDetail(
            dateSpinner.getSelectedDate(),
            timeSpinner.getSelectedTime(),
            counter.getCount()
        )
        val reservation = movieReservationOffice.makeReservation(movie, reservationDetail)
        startReservationResultActivity(button.context, reservation)
    }

    private fun startReservationResultActivity(context: Context, reservation: Reservation) {
        val intent = Intent(context, ReservationResultActivity::class.java)
        intent.putExtra(extraName, reservation)
        context.startActivity(intent)
    }
}
