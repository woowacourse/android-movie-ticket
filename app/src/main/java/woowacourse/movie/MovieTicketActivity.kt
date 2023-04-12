package woowacourse.movie

import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.domain.BookingPrice
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.MovieTime
import woowacourse.movie.domain.PeopleCount
import java.text.DecimalFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

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
        val date = getDateFromIntent()
        val time = getTimeFromIntent()
        findViewById<TextView>(R.id.ticket_title).text = movie.title
        findViewById<TextView>(R.id.ticket_date).text = "${date.format()} $time"
    }

    private fun getMovieFromIntent() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        intent.getSerializableExtra("movie", Movie::class.java)
    } else {
        intent.getSerializableExtra("movie")
    } as Movie

    private fun getDateFromIntent() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        intent.getSerializableExtra("date", LocalDate::class.java)
    } else {
        intent.getSerializableExtra("date")
    } as LocalDate

    private fun getTimeFromIntent() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        intent.getSerializableExtra("time", MovieTime::class.java)
    } else {
        intent.getSerializableExtra("time")
    } as MovieTime

    private fun LocalDate.format(): String = format(DateTimeFormatter.ofPattern("yyyy.MM.dd"))

    private fun Int.toPeopleCount(): String = "일반 ${this}명"

    private fun BookingPrice.withUnit(): String =
        "${DecimalFormat("#,###").format(price)}원 (현장 결제)"
}
