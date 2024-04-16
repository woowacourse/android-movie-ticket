package woowacourse.movie

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ReservationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)

        val movie = intent.getSerializableExtra("data") as? Movie

        // movie 객체가 null이 아닌 경우에만 UI 업데이트를 수행합니다.
        movie?.let { movie ->
            findViewById<ImageView>(R.id.poster).setImageResource(movie.poster)
            findViewById<TextView>(R.id.title).text = movie.title
            findViewById<TextView>(R.id.content).text = movie.content
            findViewById<TextView>(R.id.opening_day).text = "상영일: ${movie.openingDay}"
            findViewById<TextView>(R.id.running_time).text = "러닝타임: ${movie.runningTime}분"
        } ?: run {
        }
    }
}
