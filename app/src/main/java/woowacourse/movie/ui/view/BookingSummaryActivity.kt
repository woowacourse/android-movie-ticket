package woowacourse.movie.ui.view

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.model.BookedMovie

class BookingSummaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reservation)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val bookedMovie = intent.intentSerializable("Booking", BookedMovie::class.java)
        val title = findViewById<TextView>(R.id.title)
        val screeningDate = findViewById<TextView>(R.id.screeningDate)
        title.text = bookedMovie.title
        screeningDate.text = bookedMovie.screeningDate.toString()
    }
}