package woowacourse.movie

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.domain.Reservation
import java.text.NumberFormat
import java.time.format.DateTimeFormatter
import java.util.Locale

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
            findViewById<TextView>(R.id.movie_reservation_result_title).text =
                reservation.movie.title

            val dateFormat = DateTimeFormatter.ofPattern(getString(R.string.reservation_datetime_format))
            findViewById<TextView>(R.id.movie_reservation_result_date).text =
                dateFormat.format(reservation.detail.date)

            findViewById<TextView>(R.id.movie_reservation_result_people_count).text =
                getString(R.string.reservation_people_count).format(reservation.detail.peopleCount)

            val formattedPrice =
                NumberFormat.getNumberInstance(Locale.US).format(reservation.detail.getTotalPrice())

            findViewById<TextView>(R.id.movie_reservation_result_price).text =
                getString(R.string.reservation_price).format(formattedPrice)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
