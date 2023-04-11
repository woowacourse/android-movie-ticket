package woowacourse.movie

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.Price
import woowacourse.movie.domain.TicketingInfo

class TicketingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticketing)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val intent = intent
        val movie: Movie = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("movie", Movie::class.java)
                ?: throw IllegalArgumentException("오류가 발생했습니다.")
        } else {
            intent.getSerializableExtra("movie") as Movie
        }

        val image = findViewById<ImageView>(R.id.img_movie)
        val title = findViewById<TextView>(R.id.text_title)
        val playingDate = findViewById<TextView>(R.id.text_playing_date)
        val runningTime = findViewById<TextView>(R.id.text_running_time)
        val description = findViewById<TextView>(R.id.text_description)
        val countText = findViewById<TextView>(R.id.text_count)
        val minusButton = findViewById<Button>(R.id.btn_minus)
        val plusButton = findViewById<Button>(R.id.btn_plus)
        val ticketingButton = findViewById<Button>(R.id.btn_ticketing)

        image.setImageResource(movie.image)
        title.text = movie.title
        playingDate.text = movie.playingDate
        runningTime.text = movie.runningTime.toString()
        description.text = movie.description

        var count = 1
        minusButton.setOnClickListener {
            if (count > 1) {
                count--
                countText.text = count.toString()
            }
        }
        plusButton.setOnClickListener {
            countText.text = (++count).toString()
        }

        ticketingButton.setOnClickListener {
            val intent = Intent(this, MovieTicketActivity::class.java)
            val ticketingInfo = TicketingInfo(movie.title, movie.playingDate, count, Price(), "현장")
            intent.putExtra("ticketingInfo", ticketingInfo)
            startActivity(intent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}
