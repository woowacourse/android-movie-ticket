package woowacourse.movie

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.MainActivity.Companion.EXTRA_MOVIE_ID

class TicketingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticketing)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val movieId = intent.getIntExtra(EXTRA_MOVIE_ID, -1)
        val movie = MOVIES.find { it.id == movieId }
        movie?.let {
            findViewById<ImageView>(R.id.iv_thumbnail).apply { }
            findViewById<TextView>(R.id.tv_title).apply { text = it.title }
            findViewById<TextView>(R.id.tv_date).apply { text = "$text ${it.date}" }
            findViewById<TextView>(R.id.tv_running_time).apply { text = "${text}${it.runningTime}" }
            findViewById<TextView>(R.id.tv_introduction).apply { text = it.introduction }
        }

        var count = 1
        val minusButton = findViewById<Button>(R.id.btn_minus)
        val plusButton = findViewById<Button>(R.id.btn_plus)
        val countText = findViewById<TextView>(R.id.tv_count).apply { text = count.toString() }

        minusButton.setOnClickListener {
            if (count > 1) {
                count--
                countText.text = count.toString()
            }
        }

        plusButton.setOnClickListener {
            count++
            countText.text = count.toString()
        }
    }
}
