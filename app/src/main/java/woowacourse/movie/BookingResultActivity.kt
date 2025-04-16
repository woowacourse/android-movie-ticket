package woowacourse.movie

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class BookingResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_result)

        val title = findViewById<TextView>(R.id.title)
        val date = findViewById<TextView>(R.id.date)
        val time = findViewById<TextView>(R.id.time)
        val audienceCount = findViewById<TextView>(R.id.count)
        val money = findViewById<TextView>(R.id.money)

        title.text = intent.getStringExtra("TITLE")
        date.text = intent.getStringExtra("DATE")
        time.text = intent.getStringExtra("TIME")
        audienceCount.text = String.format(resources.getString(R.string.people_count), intent.getStringExtra("COUNT"))
        money.text = String.format(resources.getString(R.string.payment), intent.getStringExtra("COUNT").toString().toInt() * 13000)
    }
}
