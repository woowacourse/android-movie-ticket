package woowacourse.movie

import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.model.Movie
import java.time.format.DateTimeFormatter

class ReservationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reservation)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
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

        val movieTitleTextView = findViewById<TextView>(R.id.tv_reservation_title)
        val screeningDateTextView = findViewById<TextView>(R.id.tv_reservation_screening_date)
        movieTitleTextView.text = data?.title
        screeningDateTextView.text =
            data?.screeningDate?.format(
                DateTimeFormatter.ofPattern("yyyy.MM.dd"),
            )
        val posterImageView = findViewById<ImageView>(R.id.iv_reservation_poster)
        val poster =
            AppCompatResources.getDrawable(
                this,
                data?.poster ?: R.drawable.lalaland,
            )
        posterImageView.setImageDrawable(poster)
        val runningTimeTextView = findViewById<TextView>(R.id.tv_reservation_running_time)
        val runningTime = data?.runningTime
        runningTimeTextView.text = getString(R.string.movie_running_time).format(runningTime)
    }
}
