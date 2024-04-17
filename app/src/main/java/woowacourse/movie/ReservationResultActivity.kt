package woowacourse.movie

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ReservationResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_result)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        
        val title = intent.getStringExtra("title") ?: ""
        val screeningDate = intent.getStringExtra("screeningDate") ?: ""
        val reservationCount = intent.getIntExtra("reservationCount", 0)
        val totalPrice = intent.getIntExtra("totalPrice", 0)

        findViewById<TextView>(R.id.title).text = title
        findViewById<TextView>(R.id.screeningDate).text = screeningDate
        findViewById<TextView>(R.id.reservationCount).text =
            this.getString(R.string.reservation_count_format, reservationCount)
        findViewById<TextView>(R.id.totalPrice).text =
            this.getString(R.string.reservation_total_price_format, totalPrice)
    }
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
