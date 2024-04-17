package woowacourse.movie

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.model.MovieTicket

class MovieDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val posterImageId = intent.getIntExtra("posterImageId", 0)
        val title = intent.getStringExtra("title") ?: ""
        val screeningDate = intent.getStringExtra("screeningDate") ?: ""
        val runningTime = intent.getIntExtra("runningTime", 0)
        val summary = intent.getStringExtra("summary") ?: ""

        val reservationCountTextView = findViewById<TextView>(R.id.reservationCount)

        val movieTicket = MovieTicket(title, screeningDate)

        findViewById<ImageView>(R.id.posterImage).setImageResource(posterImageId)
        findViewById<TextView>(R.id.title).text = title
        findViewById<TextView>(R.id.screeningDate).text = screeningDate
        findViewById<TextView>(R.id.runningTime).text =
            getString(R.string.running_time_format, runningTime)
        findViewById<TextView>(R.id.summary).text = summary

        findViewById<TextView>(R.id.minusButton).setOnClickListener {
            movieTicket.minusCount()
            reservationCountTextView.text = movieTicket.count.toString()
        }

        findViewById<TextView>(R.id.plusButton).setOnClickListener {
            movieTicket.plusCount()
            reservationCountTextView.text = movieTicket.count.toString()
        }

        findViewById<TextView>(R.id.reserveButton).setOnClickListener {
            val intent = Intent(this, ReservationResultActivity::class.java)
            intent.putExtra("title", movieTicket.movieTitle)
            intent.putExtra("screeningDate", movieTicket.screeningDate)
            intent.putExtra("reservationCount", movieTicket.count)
            intent.putExtra("totalPrice", movieTicket.totalPrice())
            startActivity(intent)
        }
    }
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
