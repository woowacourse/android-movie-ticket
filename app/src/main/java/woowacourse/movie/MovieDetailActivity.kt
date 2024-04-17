package woowacourse.movie

import android.content.Intent
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.model.MovieTicket

class MovieDetailActivity : BaseActivity() {
    private lateinit var movieTicket: MovieTicket
    override fun getLayoutResId(): Int = R.layout.activity_movie_detail
    
    override fun onCreateSetup() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        
        val posterImageId = intent.getIntExtra("posterImageId", 0)
        val title = intent.getStringExtra("title") ?: ""
        val screeningDate = intent.getStringExtra("screeningDate") ?: ""
        val runningTime = intent.getIntExtra("runningTime", 0)
        val summary = intent.getStringExtra("summary") ?: ""
        
        movieTicket = MovieTicket(title, screeningDate)
        
        setupViews(posterImageId, title, screeningDate, runningTime, summary)
        setupReservationCountButton()
        setupReserveButton()
    }
    
    private fun setupViews(
        posterImageId: Int,
        title: String,
        screeningDate: String,
        runningTime: Int,
        summary: String
    ) {
        findViewById<ImageView>(R.id.posterImage).setImageResource(posterImageId)
        findViewById<TextView>(R.id.title).text = title
        findViewById<TextView>(R.id.screeningDate).text = screeningDate
        findViewById<TextView>(R.id.runningTime).text =
            getString(R.string.running_time_format, runningTime)
        findViewById<TextView>(R.id.summary).text = summary
    }
    
    private fun setupReservationCountButton() {
        val reservationCountTextView = findViewById<TextView>(R.id.reservationCount)
        
        findViewById<TextView>(R.id.minusButton).setOnClickListener {
            movieTicket.minusCount()
            reservationCountTextView.text = movieTicket.count.toString()
        }
        
        findViewById<TextView>(R.id.plusButton).setOnClickListener {
            movieTicket.plusCount()
            reservationCountTextView.text = movieTicket.count.toString()
        }
    }
    
    private fun setupReserveButton() {
        findViewById<TextView>(R.id.reserveButton).setOnClickListener {
            val intent = Intent(this, ReservationResultActivity::class.java)
            intent.putExtra("title", movieTicket.movieTitle)
            intent.putExtra("screeningDate", movieTicket.screeningDate)
            intent.putExtra("reservationCount", movieTicket.count)
            intent.putExtra("totalPrice", movieTicket.totalPrice())
            startActivity(intent)
        }
    }
}
