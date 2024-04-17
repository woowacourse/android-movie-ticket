package woowacourse.movie

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ReservationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)
        Log.d("test", intent.getStringExtra("movieTitle").toString())

        setUp(intent, this)
    }

    fun setUp(
        intent: Intent,
        context: Context,
    ) {
        val imageView: ImageView = findViewById(R.id.reservation_imageview)
        val titleTextView: TextView = findViewById(R.id.reservation_title_textview)
        val screenDateTextView: TextView = findViewById(R.id.reservation_screen_date_textview)
        val runningTimeTextView: TextView = findViewById(R.id.reservation_running_time_textview)
        val description: TextView = findViewById(R.id.reservation_description)

        imageView.setImageResource(intent.getIntExtra("image", 0))
        titleTextView.text = intent.getStringExtra("title")
        screenDateTextView.text = intent.getStringExtra("screenDate")
        runningTimeTextView.text = intent.getStringExtra("runningTime")
        description.text = intent.getStringExtra("description")
    }
}
