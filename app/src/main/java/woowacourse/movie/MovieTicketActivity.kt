package woowacourse.movie

import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.domain.MovieTicket
import woowacourse.movie.domain.PeopleCount
import java.text.DecimalFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MovieTicketActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_ticket)

        setTicketInfo()
    }

    private fun setTicketInfo() {
        val ticket = getTicketFromIntent()
        findViewById<TextView>(R.id.ticket_title).text = ticket.title
        findViewById<TextView>(R.id.ticket_date).text = "${ticket.date.format()} ${ticket.time}"
        findViewById<TextView>(R.id.ticket_people_count).text = ticket.peopleCount.format()
        findViewById<TextView>(R.id.ticket_price).text = ticket.getPriceWithUnit()
    }

    private fun getTicketFromIntent() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        intent.getSerializableExtra("ticket", MovieTicket::class.java)
    } else {
        intent.getSerializableExtra("ticket")
    } as MovieTicket

    private fun LocalDate.format(): String = format(DateTimeFormatter.ofPattern("yyyy.MM.dd"))

    private fun PeopleCount.format(): String = "일반 ${count}명"

    private fun MovieTicket.getPriceWithUnit(): String =
        "${DecimalFormat("#,###").format(getPrice())}원 (현장 결제)"
}
