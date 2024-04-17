package woowacourse.movie.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import woowacourse.movie.R
import woowacourse.movie.model.theater.Theater

class MovieDetailActivity : AppCompatActivity() {
    private var ticketNum = 1

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_detail)
        val theater = intent.getSerializableExtra("Theater", Theater::class.java)
        val thumbnail = findViewById<ImageView>(R.id.movie_thumbnail)
        thumbnail.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.movie_making_poster))
        val movie = theater?.movie

        if (movie != null) {
            findViewById<TextView>(R.id.movie_title_large).text =
                movie.title.toString()
            findViewById<TextView>(R.id.movie_release_date_large).text =
                movie.releaseDate.toString()
            findViewById<TextView>(R.id.movie_running_time).text =
                movie.runningTime.toString()
            findViewById<TextView>(R.id.movie_synopsis).text =
                movie.synopsis.toString()
        }

        val ticketPlusButton = findViewById<Button>(R.id.plus_button)
        val ticketMinusButton = findViewById<Button>(R.id.minus_button)
        val ticketBuyButton = findViewById<Button>(R.id.buy_ticket_button)
        val numberOfPurchases = findViewById<TextView>(R.id.quantity_text_view)

        ticketPlusButton.setOnClickListener {
            ticketNum += 1
            numberOfPurchases.text = ticketNum.toString()
        }
        ticketMinusButton.setOnClickListener {
            if (ticketNum > 0) ticketNum -= 1
            numberOfPurchases.text = ticketNum.toString()
        }

        ticketBuyButton.setOnClickListener {
            val intent = Intent(this, PurchaseConfirmationActivity::class.java).apply {
                putExtra("ticketNum", ticketNum)
                putExtra("Theater", theater)
            }
            startActivity(intent)
        }
    }
}