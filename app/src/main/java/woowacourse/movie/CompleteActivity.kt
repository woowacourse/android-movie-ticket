package woowacourse.movie

import android.icu.text.DecimalFormat
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.domain.Ticket
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CompleteActivity : AppCompatActivity() {
    private val priceFormatter = DecimalFormat("#,###")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_complete)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var ticket = intent.getSerializableExtra("ticket") as? Ticket
        if (ticket == null) {
            ticket = Ticket("", LocalDateTime.of(2025, 1, 1, 1, 1), 1)
        }

        val formatter = DateTimeFormatter.ofPattern("yyyy.M.d. HH:mm")
        val dateTimeFormat = ticket.date.format(formatter)

        val movieTitleTextView = findViewById<TextView>(R.id.movie_title)
        val movieDateTextView = findViewById<TextView>(R.id.movie_date)
        val moviePersonnel = findViewById<TextView>(R.id.movie_personnel)
        val movieTotalPrice = findViewById<TextView>(R.id.movie_total_price)

        movieTitleTextView.text = ticket.title
        movieDateTextView.text = dateTimeFormat
        moviePersonnel.text = getString(R.string.moviePersonnel, ticket.personnel)
        movieTotalPrice.text = getString(R.string.movieTotalPrice, totalPrice(Ticket.PRICE, ticket.personnel))
    }

    private fun totalPrice(
        price: Int,
        personnel: Int,
    ): String {
        return priceFormatter.format(price * personnel).toString()
    }
}
