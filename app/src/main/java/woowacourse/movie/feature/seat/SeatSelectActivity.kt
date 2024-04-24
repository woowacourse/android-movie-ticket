package woowacourse.movie.feature.seat

import android.os.Bundle
import android.widget.Button
import android.widget.TableLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R

class SeatSelectActivity : AppCompatActivity() {
    private val seatTable by lazy { findViewById<TableLayout>(R.id.seat_table) }
    private val titleText by lazy { findViewById<TextView>(R.id.title_text) }
    private val reservationAmountText by lazy { findViewById<TextView>(R.id.reservation_amount_text) }
    private val confirmButton by lazy { findViewById<Button>(R.id.confirm_button) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_select)
    }
}
