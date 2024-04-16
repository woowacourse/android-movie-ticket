package woowacourse.movie

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import domain.Ticket

class ReservationDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val ticket = Ticket()
        val poster = findViewById<ImageView>(R.id.image_view_reservation_detail_poster)
        val title = findViewById<TextView>(R.id.text_view_reservation_detail_title)
        val screeningDate = findViewById<TextView>(R.id.text_view_reservation_screening_date)
        val runningTime = findViewById<TextView>(R.id.text_view_reservation_running_time)
        val summary = findViewById<TextView>(R.id.text_view_reservation_summary)
        val reservationButton = findViewById<Button>(R.id.button_reservation_detail_finished)
        val plusButton = findViewById<Button>(R.id.button_reservation_detail_plus)
        val minusButton = findViewById<Button>(R.id.button_reservation_detail_minus)
        val numberOfTickets = findViewById<TextView>(R.id.text_view_reservation_detail_number_of_tickets)

        val titleDetail = intent.getStringExtra("title")
        val screeningDateDetail = intent.getStringExtra("screeningDate")
        val runningTimeDetail = intent.getStringExtra("runningTime")
        val summaryDetail = intent.getStringExtra("summary")

        poster.setImageResource(intent.getIntExtra("poster", 0))
        title.text = titleDetail
        screeningDate.text = screeningDateDetail
        runningTime.text = runningTimeDetail
        summary.text = summaryDetail

        plusButton.setOnClickListener {
            ticket.increaseCount()
            numberOfTickets.text = ticket.count.toString()
        }

        minusButton.setOnClickListener {
            ticket.decreaseCount()
            numberOfTickets.text = ticket.count.toString()
        }

        reservationButton.setOnClickListener {
            val intent = Intent(this, ReservationFinishedActivity::class.java)
            intent.putExtra("title", titleDetail)
            intent.putExtra("screeningDate", screeningDateDetail)
            intent.putExtra("runningTime", runningTimeDetail)
            intent.putExtra("numberOfTickets", ticket.count)
            startActivity(intent)
        }
    }
}
