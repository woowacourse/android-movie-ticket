package woowacourse.movie

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.utils.formatTimestamp

class MovieDetailActivity : AppCompatActivity() {
    private lateinit var reservationCompleteActivityResultLauncher: ActivityResultLauncher<Intent>
    private var count: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val movie =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getSerializableExtra("movie", Movie::class.java)
            } else {
                intent.getSerializableExtra("movie") as Movie
            }

        reservationCompleteActivityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { }

        movie?.let { movie ->
            findViewById<ImageView>(R.id.detailImage).setImageResource(movie.thumbnail)
            findViewById<TextView>(R.id.detailTitle).text = movie.title
            findViewById<TextView>(R.id.detailDate).text = formatTimestamp(movie.date)
            findViewById<TextView>(R.id.detailRunningTime).text = "${movie.runningTime}분"
            findViewById<TextView>(R.id.detailDescription).text = movie.description
            findViewById<TextView>(R.id.reservationCount).text = count.toString()

            findViewById<Button>(R.id.minus).setOnClickListener {
                if (count <= 1) return@setOnClickListener
                findViewById<TextView>(R.id.reservationCount).text =
                    (--count).toString()
            }
            findViewById<Button>(R.id.plus).setOnClickListener {
                findViewById<TextView>(R.id.reservationCount).text =
                    (++count).toString()
            }
            findViewById<Button>(R.id.reservationComplete).setOnClickListener {
                val intent = Intent(this, ReservationCompleteActivity::class.java)
                intent.putExtra("ticket", Ticket(movie.title, movie.date, count))
                reservationCompleteActivityResultLauncher.launch(intent)
            }
        }
    }
}
