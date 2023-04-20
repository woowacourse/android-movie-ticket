package woowacourse.movie.activity.seatselect

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R

class SeatSelectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_select)
        val seatView = SeatView(findViewById(R.id.layout_seat_area))
        seatView.set()
    }
}
