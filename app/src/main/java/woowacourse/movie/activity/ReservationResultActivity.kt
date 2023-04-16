package woowacourse.movie.activity

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.domain.ReservationDetail
import woowacourse.movie.getSerializable
import woowacourse.movie.view.MovieView
import woowacourse.movie.view.Reservation
import woowacourse.movie.view.mapper.ReservationDetailMapper.toDomain
import java.text.NumberFormat
import java.time.format.DateTimeFormatter
import java.util.Locale

class ReservationResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_result)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val reservation =
            intent.extras?.getSerializable<Reservation>(Reservation.RESERVATION_EXTRA_NAME)

        if (reservation != null) {
            renderMovie(
                movie = reservation.movie, title = findViewById(R.id.movie_reservation_result_title)
            )

            renderReservationDetail(
                reservationDetail = reservation.detail.toDomain(),
                date = findViewById(R.id.movie_reservation_result_date),
                peopleCount = findViewById(R.id.movie_reservation_result_people_count),
                price = findViewById(R.id.movie_reservation_result_price),
            )
        }
    }

    private fun renderMovie(
        movie: MovieView,
        title: TextView? = null
    ) {
        title?.text = movie.title
    }

    private fun renderReservationDetail(
        reservationDetail: ReservationDetail,
        date: TextView? = null,
        peopleCount: TextView? = null,
        price: TextView? = null,
    ) {
        if (date != null) {
            val dateFormat =
                DateTimeFormatter.ofPattern(date.context.getString(R.string.reservation_datetime_format))
            date.text = dateFormat.format(reservationDetail.date)
        }

        if (peopleCount != null) {
            peopleCount.text = peopleCount.context.getString(R.string.reservation_people_count)
                .format(reservationDetail.peopleCount)
        }

        val formattedPrice =
            NumberFormat.getNumberInstance(Locale.US).format(reservationDetail.getTotalPrice())

        if (price != null) {
            price.text = price.context.getString(R.string.reservation_price).format(formattedPrice)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
