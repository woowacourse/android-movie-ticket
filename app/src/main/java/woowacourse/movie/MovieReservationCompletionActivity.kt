package woowacourse.movie

import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.domain.Ticket
import java.time.format.DateTimeFormatter

class MovieReservationCompletionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_movie_reservation_completion)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val ticket =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getParcelableExtra("ticket", Ticket::class.java)
            } else {
                @Suppress("DEPRECATION")
                intent.getParcelableExtra("ticket")
            }
        if (ticket == null) {
            finish()
            return
        }

        val title = findViewById<TextView>(R.id.movie_title)
        val screeningDate = findViewById<TextView>(R.id.screening_date)

        title.text = ticket.movie.title
        screeningDate.text =
            ticket.movie.startDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"))
    }
}
