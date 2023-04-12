package woowacourse.movie

import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.domain.BookingPrice
import woowacourse.movie.domain.Date
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.PeopleCount
import java.text.DecimalFormat

class MovieTicketActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_ticket)

        setMovieInfo()

        val peopleCount = intent.getIntExtra("count", PeopleCount.MINIMUM_COUNT)
        val bookingPrice = BookingPrice.of(peopleCount)
        findViewById<TextView>(R.id.ticket_people_count).text = peopleCount.toPeopleCount()
        findViewById<TextView>(R.id.ticket_price).text = bookingPrice.withUnit()
    }

    private fun setMovieInfo() {
        val movie = getMovieFromIntent()
        findViewById<TextView>(R.id.ticket_title).text = movie.title
        findViewById<TextView>(R.id.ticket_date).text = movie.date.toScreenDate()
    }

    private fun getMovieFromIntent() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        intent.getSerializableExtra("movie", Movie::class.java)
    } else {
        intent.getSerializableExtra("movie")
    } as Movie

    private fun Date.toScreenDate(): String = "$year.$month.$day"

    private fun Int.toPeopleCount(): String = "일반 ${this}명"

    private fun BookingPrice.withUnit(): String =
        "${DecimalFormat("#,###").format(price)}원 (현장 결제)"
}
