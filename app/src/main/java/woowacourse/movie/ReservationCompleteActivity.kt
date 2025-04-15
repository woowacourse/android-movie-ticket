package woowacourse.movie

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

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

        val movieTitle: String = intent.getStringExtra(MOVIE_TITLE_KEY) ?: "라라랜드"
        val screeningDate: String = intent.getStringExtra(MOVIE_SCREENING_DATE_KEY) ?: "2025.04.15"

        val movieTitleTextView = findViewById<TextView>(R.id.tv_reservation_complete_title)
        val screeningDateTextView =
            findViewById<TextView>(R.id.tv_reservation_complete_screening_date)
        movieTitleTextView.text = movieTitle
        screeningDateTextView.text = screeningDate
    }

    companion object {
        const val MOVIE_TITLE_KEY = "title"
        const val MOVIE_SCREENING_DATE_KEY = "screeningDate"
    }
}
