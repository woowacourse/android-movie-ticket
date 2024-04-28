package woowacourse.movie.ui.result

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.domain.Ticket
import java.io.Serializable
import java.text.DecimalFormat
import java.time.format.DateTimeFormatter

class ResultActivity : AppCompatActivity() {
    private lateinit var resultViewGroup: ResultViewGroup
    private lateinit var title: String
    private lateinit var ticket: Ticket

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        title = intent.getStringExtra("screenTitle") ?: ""
        ticket = intent.intentSerializable("ticket", Ticket::class.java) ?: Ticket(-1)
        bindViews()
        initViews()
    }

    private fun <T : Serializable> Intent.intentSerializable(
        key: String,
        clazz: Class<T>,
    ): T? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            this.getSerializableExtra(key, clazz)
        } else {
            this.getSerializableExtra(key) as T?
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }

    fun bindViews() {
        resultViewGroup = ResultViewGroup(
            titleTextView = findViewById(R.id.result_title_textview),
            screenDateTime = findViewById(R.id.result_screen_time_textview),
            seatCountTextView = findViewById(R.id.result_seats_count_textview),
            seatsTextView = findViewById(R.id.result_seats_textview),
            priceTextView = findViewById(R.id.result_price_textview)
        )
    }

    fun initViews() {
        resultViewGroup.setUpData(title, ticket)
    }
}

class ResultViewGroup(
    val titleTextView: TextView,
    val screenDateTime: TextView,
    val seatCountTextView: TextView,
    val seatsTextView: TextView,
    val priceTextView: TextView,
) {
    fun setUpData(title: String, ticket: Ticket) {
        titleTextView.text = title
        screenDateTime.text = "${ticket.date.format(dateFormat)} ${ticket.time}"
        seatCountTextView.text = ticket.count.toString()
        seatsTextView.text = ticket.seats.seatList.sortedWith(
            compareBy(
                {it.row},
                {it.col}
            )
        ).joinToString { it.row + it.col.toString() }
        priceTextView.text = dec.format(ticket.seats.totalPrice)
    }

    companion object {
        val dec = DecimalFormat("#,###")

        val dateFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("YYYY.M.d")
    }
}