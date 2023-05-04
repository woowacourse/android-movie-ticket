package woowacourse.movie.view.activities.reservationresult

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import woowacourse.movie.R

class ReservationResultActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_result2)
    }

    companion object {
        const val RESERVATION_ID = "RESERVATION_ID"
    }
}
