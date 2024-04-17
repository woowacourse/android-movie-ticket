package woowacourse.movie.view

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import java.text.DecimalFormat

class ReservationResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_result)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        fetchData(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }

    private fun fetchData(intent: Intent) {
        val titleTextView: TextView = findViewById(R.id.result_title_textview)
        val screenDataTextView: TextView = findViewById(R.id.result_screen_date_textview)
        val countTextView: TextView = findViewById(R.id.result_count_textview)
        val priceTextView: TextView = findViewById(R.id.result_price_textview)

        titleTextView.text = intent.getStringExtra("title")
        screenDataTextView.text = intent.getStringExtra("screenDate")
        countTextView.text = intent.getStringExtra("count")
        priceTextView.text = DecimalFormat("#,###").format(intent.getIntExtra("price", 0))
    }
}
