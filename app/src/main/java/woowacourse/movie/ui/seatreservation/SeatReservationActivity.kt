package woowacourse.movie.ui.seatreservation

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R

class SeatReservationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_reservation)

        val button = findViewById<TextView>(R.id.tv_seat_reservation_check_btn)
        button.setOnClickListener {
            it.isSelected = !it.isSelected
        }
    }
}
