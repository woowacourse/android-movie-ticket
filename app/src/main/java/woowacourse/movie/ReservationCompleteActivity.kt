package woowacourse.movie

import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.model.Movie
import java.time.format.DateTimeFormatter

class ReservationCompleteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reservation_complete)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.reservation_complete)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val data =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getSerializableExtra("data", Movie::class.java)
            } else {
                intent.getSerializableExtra("data") as Movie
            }

        val movieTitleTextView = findViewById<TextView>(R.id.tv_reservation_complete_title)
        val screeningDateTextView =
            findViewById<TextView>(R.id.tv_reservation_complete_screening_date)
        movieTitleTextView.text = data?.title
        screeningDateTextView.text =
            data?.screeningDate?.format(
                DateTimeFormatter.ofPattern("yyyy.MM.dd"),
            )

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}
