package woowacourse.movie.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.domain.Reservation
import woowacourse.movie.getSerializable
import woowacourse.movie.view.MovieController
import woowacourse.movie.view.ReservationDetailController

class ReservationResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_result)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val reservation =
            intent.extras?.getSerializable<Reservation>(getString(R.string.reservation_extra_name))

        if (reservation != null) {
            MovieController(
                movie = reservation.movie,
                title = findViewById(R.id.movie_reservation_result_title)
            ).render()

            ReservationDetailController(
                reservationDetail = reservation.detail,
                date = findViewById(R.id.movie_reservation_result_date),
                peopleCount = findViewById(R.id.movie_reservation_result_people_count),
                price = findViewById(R.id.movie_reservation_result_price),
            ).render()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
