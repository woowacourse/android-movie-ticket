package woowacourse.movie

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ReservationDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val poster = findViewById<ImageView>(R.id.image_view_reservation_detail_poster)
        val title = findViewById<TextView>(R.id.text_view_reservation_detail_title)
        val screeningDate = findViewById<TextView>(R.id.text_view_reservation_screening_date)
        val runningTime = findViewById<TextView>(R.id.text_view_reservation_running_time)
        val summary = findViewById<TextView>(R.id.text_view_reservation_summary)

        poster.setImageResource(intent.getIntExtra("poster", 0))
        title.text = intent.getStringExtra("title")
        screeningDate.text = intent.getStringExtra("screeningDate")
        runningTime.text = intent.getStringExtra("runningTime")
        summary.text = intent.getStringExtra("summary")
    }
}
