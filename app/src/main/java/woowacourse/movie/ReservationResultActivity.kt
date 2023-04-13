package woowacourse.movie

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.domain.Reservation

class ReservationResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_result)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val reservation = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.extras?.getSerializable(
                getString(R.string.reservation_extra_name),
                Reservation::class.java,
            )
        } else {
            intent.extras?.getSerializable(getString(R.string.reservation_extra_name)) as Reservation
        }

        if (reservation != null) {
            MovieController(
                this,
                reservation.movie,
                title = findViewById(R.id.movie_reservation_result_title)
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
