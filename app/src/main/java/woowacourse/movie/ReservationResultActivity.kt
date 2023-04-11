package woowacourse.movie

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.domain.Reservation
import java.text.SimpleDateFormat
import java.util.Locale

class ReservationResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_result)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val reservation = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.extras?.getSerializable("reservation", Reservation::class.java)
        } else {
            intent.extras?.getSerializable("reservation") as Reservation
        }

        if (reservation != null) {
            findViewById<TextView>(R.id.movie_reservation_result_title).text =
                reservation.movie.title

            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA)
            findViewById<TextView>(R.id.movie_reservation_result_date).text =
                dateFormat.format(reservation.date)

            findViewById<TextView>(R.id.movie_reservation_result_people_count).text =
                "일반 %d명".format(reservation.peopleCount)

            findViewById<TextView>(R.id.movie_reservation_result_price).text =
                "%d원".format(reservation.price)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
