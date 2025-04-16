package woowacourse.movie

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class BookingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_booking)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.booking)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val title = findViewById<TextView>(R.id.title)
        val startDate = findViewById<TextView>(R.id.start_date)
        val endDate = findViewById<TextView>(R.id.end_date)
        val runningTime = findViewById<TextView>(R.id.running_time)
        val poster = findViewById<ImageView>(R.id.movie_poster)

        poster.setImageResource(intent.getIntExtra("POSTER",0))
        title.text = intent.getStringExtra("TITLE")
        startDate.text = intent.getStringExtra("START_DATE")
        endDate.text = intent.getStringExtra("END_DATE")
        runningTime.text = intent.getStringExtra("RUNNING_TIME")
    }
}
