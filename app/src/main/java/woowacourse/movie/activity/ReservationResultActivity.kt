package woowacourse.movie.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.dto.ReservationDto
import woowacourse.movie.dto.ReservationDtoConverter
import woowacourse.movie.getSerializableCompat
import woowacourse.movie.view.MovieController
import woowacourse.movie.view.ReservationDetailController

class ReservationResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_result)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val reservationDto =
            intent.extras?.getSerializableCompat<ReservationDto>(getString(R.string.reservation_extra_name))
        val reservation = reservationDto?.let { ReservationDtoConverter().convertDtoToModel(it) }

        if (reservation != null) {
            MovieController(
                this, reservation.movie, title = findViewById(R.id.movie_reservation_result_title)
            ).render()

            ReservationDetailController(
                this,
                reservation.detail,
                findViewById(R.id.movie_reservation_result_date),
                findViewById(R.id.movie_reservation_result_people_count),
                findViewById(R.id.movie_reservation_result_price),
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
