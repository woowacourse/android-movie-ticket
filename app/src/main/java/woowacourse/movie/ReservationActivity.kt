package woowacourse.movie

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.model.Count

class ReservationActivity : AppCompatActivity() {
    private lateinit var count: Count
    private lateinit var countTextView: TextView
    private lateinit var titleTextView: TextView
    private lateinit var screenDateTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)
        count = Count()
        fetchData(intent)
        setUpCount()
        bindReservationButton()
    }

    private fun fetchData(intent: Intent) {
        val imageView: ImageView = findViewById(R.id.reservation_imageview)
        titleTextView = findViewById(R.id.reservation_title_textview)
        screenDateTextView = findViewById(R.id.reservation_screen_date_textview)
        val runningTimeTextView: TextView = findViewById(R.id.reservation_running_time_textview)
        val description: TextView = findViewById(R.id.reservation_description)

        imageView.setImageResource(intent.getIntExtra("image", 0))
        titleTextView.text = intent.getStringExtra("title")
        screenDateTextView.text = intent.getStringExtra("screenDate")
        runningTimeTextView.text = intent.getStringExtra("runningTime")
        description.text = intent.getStringExtra("description")
    }

    private fun setUpCount() {
        countTextView = findViewById(R.id.reservation_count_textview)
        updateCountTextView()
        bindCountButtons()
    }

    private fun updateCountTextView() {
        countTextView.text = count.amount.toString()
    }

    private fun bindCountButtons() {
        val addButton: Button = findViewById(R.id.add_button)
        val subButton: Button = findViewById(R.id.sub_button)

        addButton.setOnClickListener {
            count.add()
            updateCountTextView()
        }

        subButton.setOnClickListener {
            count.sub()
            updateCountTextView()
        }
    }

    private fun bindReservationButton() {
        val reservationButton: Button = findViewById(R.id.reservation_complete_button)

        reservationButton.setOnClickListener {
            val intent = Intent(this, ReservationResultActivity::class.java)
            intent.putExtra("title", titleTextView.text)
            intent.putExtra("screenDate", screenDateTextView.text)
            intent.putExtra("count", countTextView.text)

            this.startActivity(intent)
        }
    }
}
